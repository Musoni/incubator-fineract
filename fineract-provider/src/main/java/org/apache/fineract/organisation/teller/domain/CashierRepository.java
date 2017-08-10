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
package org.apache.fineract.organisation.teller.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.persistence.NamedNativeQuery;
import java.util.List;

/**
 * Provides the domain repository for accessing, adding, modifying or deleting cashiers.
 *
 * @author Markus Geiss
 * @see org.apache.fineract.organisation.teller.domain.Cashier
 * @since 2.0.0
 */
public interface CashierRepository extends JpaRepository<Cashier, Long>, JpaSpecificationExecutor<Cashier> {
    // no added behavior

    public static final String FIND_ACTIVE_TELLER_CASHIER = "from Cashier c where c.teller.id= :tellerId and c.isActive = 1 ";

    public static final String FIND_ACTIVE_CASHIERS = "select * from m_cashiers c where c.staff_id = :staffId and c.teller_id !=:tellerId and c.is_active = 1 ";

    @Query(FIND_ACTIVE_TELLER_CASHIER)
    List<Cashier> getActiveTellerCashier(@Param("tellerId")  Long tellerId);


    @Query(value=FIND_ACTIVE_CASHIERS,nativeQuery = true)
    List<Cashier> getActiveCashier(@Param("staffId")  Long staffId, @Param("tellerId")  Long tellerId);
}
