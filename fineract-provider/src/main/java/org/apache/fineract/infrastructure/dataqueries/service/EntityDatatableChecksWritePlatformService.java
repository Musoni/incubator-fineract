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
package org.apache.fineract.infrastructure.dataqueries.service;

import org.apache.fineract.infrastructure.core.api.JsonCommand;
import org.apache.fineract.infrastructure.core.data.CommandProcessingResult;

public interface EntityDatatableChecksWritePlatformService {

    CommandProcessingResult createCheck(JsonCommand command);
    CommandProcessingResult deleteCheck(final Long entityDatatableCheckId);
    void runTheCheck(final Long entityId,final String entityName,final Long statusCode,String foreignKeyColumn);
    void runTheCheckForLoan(final Long entityId,final String entityName,final Long statusCode,String foreignKeyColumn,long productLoanId);



}