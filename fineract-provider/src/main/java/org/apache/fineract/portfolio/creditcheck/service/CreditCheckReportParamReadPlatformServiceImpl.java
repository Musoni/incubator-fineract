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

import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.fineract.infrastructure.core.service.RoutingDataSource;
import org.apache.fineract.portfolio.creditcheck.data.CreditCheckReportParamData;
import org.apache.fineract.portfolio.creditcheck.exception.CreditCheckReportParamNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

@Service
public class CreditCheckReportParamReadPlatformServiceImpl implements CreditCheckReportParamReadPlatformService {
    private final JdbcTemplate jdbcTemplate;
    
    @Autowired
    public CreditCheckReportParamReadPlatformServiceImpl(final RoutingDataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }
    
    @Override
    public CreditCheckReportParamData retrieveCreditCheckReportParameters(Long loanId, Long userId, Boolean isGroupLoan) {
        try {

            final CreditCheckReportParamMapper mapper = new CreditCheckReportParamMapper(userId);

            if(isGroupLoan)
            {
                final String sql = "select " + mapper.GroupCreditCheckReportParamSchema() + " where ml.id = ? limit 1";
                return this.jdbcTemplate.queryForObject(sql, mapper, new Object[] { loanId });
            }
            else
            {
                final String sql = "select " + mapper.IndividualCreditCheckReportParamSchema() + " where ml.id = ? limit 1";
                return this.jdbcTemplate.queryForObject(sql, mapper, new Object[] { loanId });
            }


        }
        
        catch (final EmptyResultDataAccessException e) {
            throw new CreditCheckReportParamNotFoundException(loanId);
        }
    }
    
    private static final class CreditCheckReportParamMapper implements RowMapper<CreditCheckReportParamData> {
       private final Long userId;
        
        public String IndividualCreditCheckReportParamSchema() {
            return "ml.id as loanId, mc.id as clientId, mc.office_id as officeId, mc.staff_id as staffId, "
                    + "mgc.group_id as groupId, ml.product_id as productId "
                    + "from m_loan ml "
                    + "inner join m_client mc "
                    + "on ml.client_id = mc.id "
                    + "left join m_group_client mgc "
                    + "on mgc.client_id = ml.client_id";
        }

        public String GroupCreditCheckReportParamSchema() {
            return "ml.id as loanId, 0 as clientId, mg.office_id as officeId, mg.staff_id as staffId, "
                    + "mg.id as groupId, ml.product_id as productId "
                    + "from m_loan ml "
                    + "inner join m_group mg "
                    + "on ml.group_id = mg.id ";
        }
        
        public CreditCheckReportParamMapper(Long userId) {
           this.userId = userId;
        }

        @Override
        public CreditCheckReportParamData mapRow(ResultSet rs, int rowNum) throws SQLException {
            final Long loanId = rs.getLong("loanId");
            final Long clientId = rs.getLong("clientId");
            final Long officeId = rs.getLong("officeId");
            final Long staffId = rs.getLong("staffId");
            final Long userId = this.userId;
            final Long groupId = rs.getLong("groupId");
            final Long productId = rs.getLong("productId");
            
            return CreditCheckReportParamData.instance(clientId, loanId, groupId, userId, staffId, officeId, productId);
        }
    }
}
