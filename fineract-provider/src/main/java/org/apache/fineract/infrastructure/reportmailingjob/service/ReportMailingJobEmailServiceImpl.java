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
package org.apache.fineract.infrastructure.reportmailingjob.service;

import java.util.Collection;
import java.util.Properties;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.apache.commons.lang.StringUtils;
import org.apache.fineract.infrastructure.reportmailingjob.ReportMailingJobConstants;
import org.apache.fineract.infrastructure.reportmailingjob.data.ReportMailingJobConfigurationData;
import org.apache.fineract.infrastructure.reportmailingjob.data.ReportMailingJobEmailData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class ReportMailingJobEmailServiceImpl implements ReportMailingJobEmailService {
    private final ReportMailingJobConfigurationReadPlatformService reportMailingJobConfigurationReadPlatformService;
    private Collection<ReportMailingJobConfigurationData> reportMailingJobConfigurationDataCollection;
    
    /** 
     * ReportMailingJobEmailServiceImpl constructor
     **/
    @Autowired
    public ReportMailingJobEmailServiceImpl(final ReportMailingJobConfigurationReadPlatformService reportMailingJobConfigurationReadPlatformService) {
        this.reportMailingJobConfigurationReadPlatformService = reportMailingJobConfigurationReadPlatformService;
        
    }

    @Override
    public void sendEmailWithAttachment(ReportMailingJobEmailData reportMailingJobEmailData) {
        try {
            // get all ReportMailingJobConfiguration objects from the database
            this.reportMailingJobConfigurationDataCollection = this.reportMailingJobConfigurationReadPlatformService.
                    retrieveAllReportMailingJobConfigurations();
            
            JavaMailSenderImpl javaMailSenderImpl = new JavaMailSenderImpl();
            javaMailSenderImpl.setHost(this.getReportSmtpServer());
            javaMailSenderImpl.setPort(this.getRerportSmtpPort());
            javaMailSenderImpl.setUsername(this.getReportSmtpUsername());
            javaMailSenderImpl.setPassword(this.getReportSmtpPassword());
            javaMailSenderImpl.setJavaMailProperties(this.getJavaMailProperties());
            
            MimeMessage mimeMessage = javaMailSenderImpl.createMimeMessage();
            
            // use the true flag to indicate you need a multipart message
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
            
            mimeMessageHelper.setTo(reportMailingJobEmailData.getTo());
            mimeMessageHelper.setFrom(this.getReportSmtpFromAddress());
            mimeMessageHelper.setText(reportMailingJobEmailData.getText());
            mimeMessageHelper.setSubject(reportMailingJobEmailData.getSubject());
            
            if (reportMailingJobEmailData.getAttachment() != null) {
                mimeMessageHelper.addAttachment(reportMailingJobEmailData.getAttachment().getName(), reportMailingJobEmailData.getAttachment());
            }
            
            javaMailSenderImpl.send(mimeMessage);
        } 
        
        catch (MessagingException e) {
            // handle the exception
            e.printStackTrace();
        }
    }
    
    /** 
     * @return Properties object containing JavaMail properties 
     **/
    private Properties getJavaMailProperties() {
        Properties properties = new Properties();
        
        properties.setProperty("mail.smtp.auth", "true");
        properties.setProperty("mail.smtp.starttls.enable", "true");
        properties.setProperty("mail.smtp.ssl.trust", this.getReportSmtpServer());
        properties.setProperty("mail.smtp.from", this.getReportSmtpFromAddress());
        
        return properties;
    }
    
    /** 
     * get a report mailing job configuration object by name from collection of objects 
     * 
     * @param name -- the value of the name property
     * @return ReportMailingJobConfigurationData object
     **/
    private ReportMailingJobConfigurationData getReportMailingJobConfigurationData(final String name) {
        ReportMailingJobConfigurationData reportMailingJobConfigurationData = null;
        
        if (this.reportMailingJobConfigurationDataCollection != null && !this.reportMailingJobConfigurationDataCollection.isEmpty()) {
            for (ReportMailingJobConfigurationData reportMailingJobConfigurationDataObject : this.reportMailingJobConfigurationDataCollection) {
                String configurationName = reportMailingJobConfigurationDataObject.getName();
                
                if (!StringUtils.isEmpty(configurationName) && configurationName.equals(name)) {
                    reportMailingJobConfigurationData = reportMailingJobConfigurationDataObject;
                    break;
                }
            }
        }
        
        return reportMailingJobConfigurationData;
    }
    
    /** 
     * @return Gmail smtp server name 
     **/
    private String getReportSmtpServer() {
        final ReportMailingJobConfigurationData reportMailingJobConfigurationData = this.getReportMailingJobConfigurationData
                (ReportMailingJobConstants.REPORT_SMTP_SERVER);
        
        return (reportMailingJobConfigurationData != null) ? reportMailingJobConfigurationData.getValue() : null;
    }
    
    /** 
     * @return Gmail smtp server port number 
     **/
    private Integer getRerportSmtpPort() {
        final ReportMailingJobConfigurationData reportMailingJobConfigurationData = this.getReportMailingJobConfigurationData
                (ReportMailingJobConstants.REPORT_SMTP_PORT);
        final String portNumber = (reportMailingJobConfigurationData != null) ? reportMailingJobConfigurationData.getValue() : null;
        
        return (portNumber != null) ? Integer.parseInt(portNumber) : null;
    }
    
    /** 
     * @return Gmail smtp username 
     **/
    private String getReportSmtpUsername() {
        final ReportMailingJobConfigurationData reportMailingJobConfigurationData = this.getReportMailingJobConfigurationData
                (ReportMailingJobConstants.REPORT_SMTP_USERNAME);
        
        return (reportMailingJobConfigurationData != null) ? reportMailingJobConfigurationData.getValue() : null;
    }
    
    /** 
     * @return Gmail smtp password 
     **/
    private String getReportSmtpPassword() {
        final ReportMailingJobConfigurationData reportMailingJobConfigurationData = this.getReportMailingJobConfigurationData
                (ReportMailingJobConstants.REPORT_SMTP_PASSWORD);
        
        return (reportMailingJobConfigurationData != null) ? reportMailingJobConfigurationData.getValue() : null;
    }

    /**
     * @return Gmail smtp From Address
     **/
    private String getReportSmtpFromAddress() {
        final ReportMailingJobConfigurationData reportMailingJobConfigurationData = this.getReportMailingJobConfigurationData
                (ReportMailingJobConstants.REPORT_SMTP_FROMADDRESS);

        return (reportMailingJobConfigurationData != null) ? reportMailingJobConfigurationData.getValue() : null;
    }
}
