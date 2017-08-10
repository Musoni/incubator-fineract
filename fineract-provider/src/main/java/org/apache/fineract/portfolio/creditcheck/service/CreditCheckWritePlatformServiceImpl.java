/**
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements. See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership. The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package org.apache.fineract.portfolio.creditcheck.service;

import java.util.Map;

import org.apache.fineract.infrastructure.core.api.JsonCommand;
import org.apache.fineract.infrastructure.core.data.CommandProcessingResult;
import org.apache.fineract.infrastructure.core.data.CommandProcessingResultBuilder;
import org.apache.fineract.infrastructure.core.exception.PlatformDataIntegrityException;
import org.apache.fineract.infrastructure.core.service.RoutingDataSource;
import org.apache.fineract.infrastructure.dataqueries.domain.Report;
import org.apache.fineract.infrastructure.dataqueries.domain.ReportRepositoryWrapper;
import org.apache.fineract.portfolio.creditcheck.CreditCheckConstants;
import org.apache.fineract.portfolio.creditcheck.data.CreditCheckDataValidator;
import org.apache.fineract.portfolio.creditcheck.domain.CreditCheck;
import org.apache.fineract.portfolio.creditcheck.domain.CreditCheckRepository;
import org.apache.fineract.portfolio.creditcheck.domain.CreditCheckRepositoryWrapper;
import org.apache.fineract.portfolio.creditcheck.exception.CreditCheckCannotBeDeletedException;
import org.apache.fineract.portfolio.creditcheck.exception.CreditCheckCannotBeUpdatedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CreditCheckWritePlatformServiceImpl implements CreditCheckWritePlatformService {
    
    private final JdbcTemplate jdbcTemplate;
    private final CreditCheckRepository creditCheckRepository;
    private final CreditCheckDataValidator creditCheckDataValidator;
    private final static Logger logger = LoggerFactory.getLogger(CreditCheckWritePlatformServiceImpl.class);
    private final CreditCheckRepositoryWrapper creditCheckRepositoryWrapper;
    private final ReportRepositoryWrapper reportRepositoryWrapper;
    
    @Autowired
    public CreditCheckWritePlatformServiceImpl(final CreditCheckDataValidator creditCheckDataValidator, 
            final RoutingDataSource dataSource, final CreditCheckRepositoryWrapper creditCheckRepositoryWrapper, 
            final ReportRepositoryWrapper reportRepositoryWrapper) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
        this.creditCheckDataValidator = creditCheckDataValidator;
        this.creditCheckRepositoryWrapper = creditCheckRepositoryWrapper;
        this.creditCheckRepository = this.creditCheckRepositoryWrapper.getCreditCheckRepository();
        this.reportRepositoryWrapper = reportRepositoryWrapper;
    }

    @Override
    @Transactional
    public CommandProcessingResult createCreditCheck(final JsonCommand jsonCommand) {
        try {
            // validate the request
            this.creditCheckDataValidator.validateForCreateAction(jsonCommand);
            
            // get the stretchy Report object
            final Report stretchyReport = this.reportRepositoryWrapper.findOneThrowExceptionIfNotFound(jsonCommand.longValueOfParameterNamed(
                    CreditCheckConstants.STRETCHY_REPORT_ID_PARAM_NAME));
            
            // create an instance of CreditCheck class from the JsonCommand object
            final CreditCheck creditCheck = CreditCheck.instance(jsonCommand, stretchyReport);
            
            // save entity
            this.creditCheckRepository.save(creditCheck);
            
            return new CommandProcessingResultBuilder().withCommandId(jsonCommand.commandId()).withEntityId(creditCheck.getId()).build();
        }
        
        catch (final DataIntegrityViolationException dve) {
            handleDataIntegrityIssues(jsonCommand, dve);
            
            return CommandProcessingResult.empty();
        }
        
    }

    @Override
    @Transactional
    public CommandProcessingResult updateCreditCheck(final Long creditCheckId, final JsonCommand jsonCommand) {
        try {
            this.creditCheckDataValidator.validateForUpdateAction(jsonCommand);
            
            final CreditCheck creditCheck = this.creditCheckRepositoryWrapper.findOneThrowExceptionIfNotFound(creditCheckId);
            
            final Map<String, Object> changes = creditCheck.update(jsonCommand);
            
            // check if the stretchy report id was updated
            if (changes.containsKey(CreditCheckConstants.STRETCHY_REPORT_ID_PARAM_NAME)) {
                final Long stretchyReportId = (Long) changes.get(CreditCheckConstants.STRETCHY_REPORT_ID_PARAM_NAME);
                final Report stretchyReport = this.reportRepositoryWrapper.findOneThrowExceptionIfNotFound(stretchyReportId);
                
                // update the stretchy report
                creditCheck.update(stretchyReport);
            }
            
            if (changes.containsKey(CreditCheckConstants.IS_ACTIVE_PARAM_NAME) && (!creditCheck.isActive())) {
                
                if (creditCheck.isLoanCreditCheck() && hasExistingRelatedLoanProductEntity(creditCheckId)) {
                    throw new CreditCheckCannotBeUpdatedException("error.msg.credit.check.is.in.use", 
                            "This credit check cannot be deactivated, it is used by a loan product");
                }
            }
            
            if (!changes.isEmpty()) {
                // save and flush immediately so any data integrity exception can be handled in the "catch" block
                this.creditCheckRepository.saveAndFlush(creditCheck);
            }
            
            return new CommandProcessingResultBuilder()
                    .withCommandId(jsonCommand.commandId())
                    .withEntityId(creditCheck.getId())
                    .with(changes)
                    .build();
        }
        
        catch (final DataIntegrityViolationException dve) {
            handleDataIntegrityIssues(jsonCommand, dve);
            
            return CommandProcessingResult.empty();
        }
    }

    @Override
    @Transactional
    public CommandProcessingResult deleteCreditCheck(final Long creditCheckId) {
        final CreditCheck creditCheck = this.creditCheckRepositoryWrapper.findOneThrowExceptionIfNotFound(creditCheckId);
        
        if (creditCheck.isLoanCreditCheck() && (hasExistingRelatedLoanProductEntity(creditCheckId) 
                || hasExistingRelatedLoanEntity(creditCheckId))) {
            throw new CreditCheckCannotBeDeletedException("error.msg.credit.check.is.in.use", 
                    "This credit check cannot be deleted, it is used by a loan product");
        }
        
        // delete the credit check by setting the isDeleted property to 1 and altering the name
        creditCheck.delete();
        
        // save the credit check entity
        this.creditCheckRepository.save(creditCheck);
        
        return new CommandProcessingResultBuilder().withEntityId(creditCheckId).build();
    }
    
    /** 
     * Handle any SQL data integrity issue 
     *
     * @param jsonCommand -- JsonCommand object
     * @param dve -- data integrity exception object
     * @return None
     **/
    private void handleDataIntegrityIssues(final JsonCommand jsonCommand, final DataIntegrityViolationException dve) {
        final Throwable realCause = dve.getMostSpecificCause();
        
        if (realCause.getMessage().contains(CreditCheckConstants.NAME_PARAM_NAME)) {
            final String name = jsonCommand.stringValueOfParameterNamed(CreditCheckConstants.NAME_PARAM_NAME);
            throw new PlatformDataIntegrityException("error.msg.charge.duplicate.name", "Credit check with name `" + name + "` already exists",
                    CreditCheckConstants.NAME_PARAM_NAME, name);
        }

        logger.error(dve.getMessage(), dve);
        
        throw new PlatformDataIntegrityException("error.msg.charge.unknown.data.integrity.issue",
                "Unknown data integrity issue with resource: " + realCause.getMessage());
    }
    
    /** 
     * check if there is any loan entity associated with the credit check 
     * 
     * @param creditCheckId -- the identifier of the credit check entity to be checked
     * @return true/false
     **/
    private boolean hasExistingRelatedLoanEntity(final Long creditCheckId) {
        final String sql = "select if((exists (select 1 from m_loan_credit_check lcc where lcc.credit_check_id = ? and lcc.is_deleted = 0)) = 1, 'true', 'false')";
        final String relatedEntityFound = this.jdbcTemplate.queryForObject(sql, String.class, new Object[] { creditCheckId });
        
        return new Boolean(relatedEntityFound);
    }
    
    /** 
     * check if there is any loan product entity associated with the credit check 
     * 
     * @param creditCheckId -- the identifier of the credit check entity to be checked
     * @return true/false
     **/
    private boolean hasExistingRelatedLoanProductEntity(final Long creditCheckId) {
        final String sql = "select if((exists (select 1 from m_product_loan_credit_check lcc where lcc.credit_check_id = ?)) = 1, 'true', 'false')";
        final String relatedEntityFound = this.jdbcTemplate.queryForObject(sql, String.class, new Object[] { creditCheckId });
        
        return new Boolean(relatedEntityFound);
    }
}
