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
package org.apache.fineract.infrastructure.dataexport.domain;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface DataExportRepository extends JpaRepository<DataExport, Long>, JpaSpecificationExecutor<DataExport> { 
    /**
     * Retrieves entity where "deleted" property is set to 0 by id
     * @param id
     * @return {@link DataExport} entity
     */
    DataExport findByIdAndDeletedFalse(final Long id);
    
    /**
     * Retrieves all entities where "deleted" property is set to 0
     * 
     * @return collection of {@link DataExport} entities
     */
    Collection<DataExport> findByDeletedFalse();
}
