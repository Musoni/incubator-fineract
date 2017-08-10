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
package org.apache.fineract.portfolio.savings.data;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.joda.time.LocalDate;
import org.apache.fineract.accounting.common.AccountingRuleType;
import org.apache.fineract.accounting.glaccount.data.GLAccountData;
import org.apache.fineract.accounting.producttoaccountmapping.data.ChargeToGLAccountMapper;
import org.apache.fineract.accounting.producttoaccountmapping.data.PaymentTypeToGLAccountMapper;
import org.apache.fineract.infrastructure.codes.data.CodeValueData;
import org.apache.fineract.infrastructure.core.data.EnumOptionData;
import org.apache.fineract.organisation.monetary.data.CurrencyData;
import org.apache.fineract.portfolio.charge.data.ChargeData;
import org.apache.fineract.portfolio.paymenttype.data.PaymentTypeData;

/**
 * Immutable data object represent a savings product.
 */
public class SavingsProductData {

    private final Long id;
    private final String name;
    private final String shortName;
    private final String description;
    private final CurrencyData currency;
    private final BigDecimal nominalAnnualInterestRate;
    private final EnumOptionData interestCompoundingPeriodType;
    private final EnumOptionData interestPostingPeriodType;
    private final EnumOptionData interestCalculationType;
    private final EnumOptionData interestCalculationDaysInYearType;
    private final BigDecimal minRequiredOpeningBalance;
    private final Integer lockinPeriodFrequency;
    private final EnumOptionData lockinPeriodFrequencyType;
    private final boolean withdrawalFeeForTransfers;
    private final boolean allowOverdraft;
    private final BigDecimal overdraftLimit;
    private final BigDecimal minRequiredBalance;
    private final boolean enforceMinRequiredBalance;
    private final BigDecimal minBalanceForInterestCalculation;
    private final LocalDate startDate;
    private final LocalDate closeDate;
    private final String status;
    private final CodeValueData productGroup;

    // accounting
    private final EnumOptionData accountingRule;
    private final Map<String, Object> accountingMappings;
    private final Collection<PaymentTypeToGLAccountMapper> paymentChannelToFundSourceMappings;
    private final Collection<ChargeToGLAccountMapper> feeToIncomeAccountMappings;
    private final Collection<ChargeToGLAccountMapper> penaltyToIncomeAccountMappings;

    // charges
    private final Collection<ChargeData> charges;

    // template
    private final Collection<CodeValueData> productGroupOptions;
    private final Collection<CurrencyData> currencyOptions;
    private final Collection<EnumOptionData> interestCompoundingPeriodTypeOptions;
    private final Collection<EnumOptionData> interestPostingPeriodTypeOptions;
    private final Collection<EnumOptionData> interestCalculationTypeOptions;
    private final Collection<EnumOptionData> interestCalculationDaysInYearTypeOptions;
    private final Collection<EnumOptionData> lockinPeriodFrequencyTypeOptions;
    private final Collection<EnumOptionData> withdrawalFeeTypeOptions;
    private final Collection<PaymentTypeData> paymentTypeOptions;
    private final Collection<EnumOptionData> accountingRuleOptions;
    private final Map<String, List<GLAccountData>> accountingMappingOptions;
    private final Collection<ChargeData> chargeOptions;
    private final Collection<ChargeData> penaltyOptions;
	private final BigDecimal nominalAnnualInterestRateOverdraft;
	private final BigDecimal minOverdraftForInterestCalculation;

    //interest Rate Chart for savings product
    private final Collection<InterestRateCharts> interestRateCharts;

    private Collection<ApplyProductChargeToExistingSavingsAccountData> applyProductChargeToExistingSavingsAccount;


    public static SavingsProductData template(final CurrencyData currency, final EnumOptionData interestCompoundingPeriodType,
            final EnumOptionData interestPostingPeriodType, final EnumOptionData interestCalculationType,
            final EnumOptionData interestCalculationDaysInYearType, final EnumOptionData accountingRule,
            final Collection<CurrencyData> currencyOptions, final Collection<EnumOptionData> interestCompoundingPeriodTypeOptions,
            final Collection<EnumOptionData> interestPostingPeriodTypeOptions,
            final Collection<EnumOptionData> interestCalculationTypeOptions,
            final Collection<EnumOptionData> interestCalculationDaysInYearTypeOptions,
            final Collection<EnumOptionData> lockinPeriodFrequencyTypeOptions, final Collection<EnumOptionData> withdrawalFeeTypeOptions,
            final Collection<PaymentTypeData> paymentTypeOptions, final Collection<EnumOptionData> accountingRuleOptions,
            final Map<String, List<GLAccountData>> accountingMappingOptions, final Collection<ChargeData> chargeOptions,
            final Collection<ChargeData> penaltyOptions, final Collection<CodeValueData> productGroupOptions) {

        final Long id = null;
        final String name = null;
        final String shortName = null;
        final String description = null;
        final BigDecimal nominalAnnualInterestRate = null;
        final BigDecimal minRequiredOpeningBalance = null;
        final Integer lockinPeriodFrequency = null;
        final EnumOptionData lockinPeriodFrequencyType = null;
        final boolean withdrawalFeeForTransfers = false;
        final Map<String, Object> accountingMappings = null;
        final Collection<PaymentTypeToGLAccountMapper> paymentChannelToFundSourceMappings = null;
        final Collection<ChargeData> charges = null;
        final Collection<ChargeToGLAccountMapper> feeToIncomeAccountMappings = null;
        final Collection<ChargeToGLAccountMapper> penaltyToIncomeAccountMappings = null;
        final boolean allowOverdraft = false;
        final BigDecimal overdraftLimit = null;
        final BigDecimal minRequiredBalance = null;
        final boolean enforceMinRequiredBalance = false;
        final BigDecimal minBalanceForInterestCalculation = null;
        final BigDecimal nominalAnnualInterestRateOverdraft = null;
        final BigDecimal minOverdraftForInterestCalculation = null;
        final LocalDate startDate = null;
        final LocalDate closeDate = null;
        final String status = null;
        final CodeValueData productGroup = null;
        final Collection<InterestRateCharts> interestRateCharts = null;
        final Collection<ApplyProductChargeToExistingSavingsAccountData> applyProductChargeToExistingSavingsAccount= null;



        return new SavingsProductData(id, name, shortName, description, currency, nominalAnnualInterestRate, interestCompoundingPeriodType,
                interestPostingPeriodType, interestCalculationType, interestCalculationDaysInYearType, minRequiredOpeningBalance,
                lockinPeriodFrequency, lockinPeriodFrequencyType, withdrawalFeeForTransfers, accountingRule, accountingMappings,
                paymentChannelToFundSourceMappings, productGroupOptions, currencyOptions, interestCompoundingPeriodTypeOptions,
                interestPostingPeriodTypeOptions, interestCalculationTypeOptions, interestCalculationDaysInYearTypeOptions,
                lockinPeriodFrequencyTypeOptions, withdrawalFeeTypeOptions, paymentTypeOptions, accountingRuleOptions,
                accountingMappingOptions, charges, chargeOptions, penaltyOptions, feeToIncomeAccountMappings,
                penaltyToIncomeAccountMappings, allowOverdraft, overdraftLimit, minRequiredBalance, enforceMinRequiredBalance,
                minBalanceForInterestCalculation, nominalAnnualInterestRateOverdraft, minOverdraftForInterestCalculation,
                startDate, closeDate, status, productGroup,interestRateCharts,applyProductChargeToExistingSavingsAccount);
    }

    public static SavingsProductData withCharges(final SavingsProductData product, final Collection<ChargeData> charges) {
        return new SavingsProductData(product.id, product.name, product.shortName, product.description, product.currency,
                product.nominalAnnualInterestRate, product.interestCompoundingPeriodType, product.interestPostingPeriodType,
                product.interestCalculationType, product.interestCalculationDaysInYearType, product.minRequiredOpeningBalance,
                product.lockinPeriodFrequency, product.lockinPeriodFrequencyType, product.withdrawalFeeForTransfers,
                product.accountingRule, product.accountingMappings, product.paymentChannelToFundSourceMappings,
                product.productGroupOptions, product.currencyOptions,
                product.interestCompoundingPeriodTypeOptions, product.interestPostingPeriodTypeOptions,
                product.interestCalculationTypeOptions, product.interestCalculationDaysInYearTypeOptions,
                product.lockinPeriodFrequencyTypeOptions, product.withdrawalFeeTypeOptions, product.paymentTypeOptions,
                product.accountingRuleOptions, product.accountingMappingOptions, charges, product.chargeOptions, product.penaltyOptions,
                product.feeToIncomeAccountMappings, product.penaltyToIncomeAccountMappings, product.allowOverdraft, product.overdraftLimit,
                product.minRequiredBalance, product.enforceMinRequiredBalance, product.minBalanceForInterestCalculation,
                product.nominalAnnualInterestRateOverdraft, product.minOverdraftForInterestCalculation, product.startDate,
                product.closeDate, product.status, product.productGroup,null,null);
    }

    /**
     * Returns a {@link SavingsProductData} that contains and exist
     * {@link SavingsProductData} data with further template data for dropdowns.
     */
    public static SavingsProductData withTemplate(final SavingsProductData existingProduct, final Collection<CurrencyData> currencyOptions,
            final Collection<EnumOptionData> interestCompoundingPeriodTypeOptions,
            final Collection<EnumOptionData> interestPostingPeriodTypeOptions,
            final Collection<EnumOptionData> interestCalculationTypeOptions,
            final Collection<EnumOptionData> interestCalculationDaysInYearTypeOptions,
            final Collection<EnumOptionData> lockinPeriodFrequencyTypeOptions, final Collection<EnumOptionData> withdrawalFeeTypeOptions,
            final Collection<PaymentTypeData> paymentTypeOptions, final Collection<EnumOptionData> accountingRuleOptions,
            final Map<String, List<GLAccountData>> accountingMappingOptions, final Collection<ChargeData> chargeOptions,
            final Collection<ChargeData> penaltyOptions, final Collection<CodeValueData> productGroupOptions) {

        return new SavingsProductData(existingProduct.id, existingProduct.name, existingProduct.shortName, existingProduct.description,
                existingProduct.currency, existingProduct.nominalAnnualInterestRate, existingProduct.interestCompoundingPeriodType,
                existingProduct.interestPostingPeriodType, existingProduct.interestCalculationType,
                existingProduct.interestCalculationDaysInYearType, existingProduct.minRequiredOpeningBalance,
                existingProduct.lockinPeriodFrequency, existingProduct.lockinPeriodFrequencyType,
                existingProduct.withdrawalFeeForTransfers, existingProduct.accountingRule, existingProduct.accountingMappings,
                existingProduct.paymentChannelToFundSourceMappings, productGroupOptions, currencyOptions, interestCompoundingPeriodTypeOptions,
                interestPostingPeriodTypeOptions, interestCalculationTypeOptions, interestCalculationDaysInYearTypeOptions,
                lockinPeriodFrequencyTypeOptions, withdrawalFeeTypeOptions, paymentTypeOptions, accountingRuleOptions,
                accountingMappingOptions, existingProduct.charges, chargeOptions, penaltyOptions,
                existingProduct.feeToIncomeAccountMappings, existingProduct.penaltyToIncomeAccountMappings, existingProduct.allowOverdraft,
                existingProduct.overdraftLimit, existingProduct.minRequiredBalance, existingProduct.enforceMinRequiredBalance,
                existingProduct.minBalanceForInterestCalculation, existingProduct.nominalAnnualInterestRateOverdraft,
                existingProduct.minOverdraftForInterestCalculation, existingProduct.startDate, existingProduct.closeDate,
                existingProduct.status, existingProduct.productGroup,null,null);
    }

    public static SavingsProductData withAccountingDetails(final SavingsProductData existingProduct,
            final Map<String, Object> accountingMappings,
            final Collection<PaymentTypeToGLAccountMapper> paymentChannelToFundSourceMappings,
            final Collection<ChargeToGLAccountMapper> feeToIncomeAccountMappings,
            final Collection<ChargeToGLAccountMapper> penaltyToIncomeAccountMappings) {

        final Collection<CurrencyData> currencyOptions = null;
        final Collection<EnumOptionData> interestCompoundingPeriodTypeOptions = null;
        final Collection<EnumOptionData> interestPostingPeriodTypeOptions = null;
        final Collection<EnumOptionData> interestCalculationTypeOptions = null;
        final Collection<EnumOptionData> interestCalculationDaysInYearTypeOptions = null;
        final Collection<EnumOptionData> lockinPeriodFrequencyTypeOptions = null;
        final Collection<EnumOptionData> withdrawalFeeTypeOptions = null;
        final Collection<PaymentTypeData> paymentTypeOptions = null;
        final Collection<EnumOptionData> accountingRuleOptions = null;
        final Map<String, List<GLAccountData>> accountingMappingOptions = null;
        final Collection<ChargeData> chargeOptions = null;
        final Collection<ChargeData> penaltyOptions = null;
        final Collection<CodeValueData> productGroupOptions = null;
        final Collection<InterestRateCharts> interestRateCharts = null;
        final Collection<ApplyProductChargeToExistingSavingsAccountData> applyProductChargeToExistingSavingsAccount = null;



        return new SavingsProductData(existingProduct.id, existingProduct.name, existingProduct.shortName, existingProduct.description,
                existingProduct.currency, existingProduct.nominalAnnualInterestRate, existingProduct.interestCompoundingPeriodType,
                existingProduct.interestPostingPeriodType, existingProduct.interestCalculationType,
                existingProduct.interestCalculationDaysInYearType, existingProduct.minRequiredOpeningBalance,
                existingProduct.lockinPeriodFrequency, existingProduct.lockinPeriodFrequencyType,
                existingProduct.withdrawalFeeForTransfers, existingProduct.accountingRule, accountingMappings,
                paymentChannelToFundSourceMappings, productGroupOptions, currencyOptions, interestCompoundingPeriodTypeOptions,
                interestPostingPeriodTypeOptions, interestCalculationTypeOptions, interestCalculationDaysInYearTypeOptions,
                lockinPeriodFrequencyTypeOptions, withdrawalFeeTypeOptions, paymentTypeOptions, accountingRuleOptions,
                accountingMappingOptions, existingProduct.charges, chargeOptions, penaltyOptions, feeToIncomeAccountMappings,
                penaltyToIncomeAccountMappings, existingProduct.allowOverdraft, existingProduct.overdraftLimit,
                existingProduct.minRequiredBalance, existingProduct.enforceMinRequiredBalance,
                existingProduct.minBalanceForInterestCalculation, existingProduct.nominalAnnualInterestRateOverdraft,
                existingProduct.minOverdraftForInterestCalculation, existingProduct.startDate, existingProduct.closeDate,
                existingProduct.status, existingProduct.productGroup,interestRateCharts,applyProductChargeToExistingSavingsAccount);
    }

    public static SavingsProductData instance(final Long id, final String name, final String shortName, final String description,
            final CurrencyData currency, final BigDecimal nominalAnnualInterestRate, final EnumOptionData interestCompoundingPeriodType,
            final EnumOptionData interestPostingPeriodType, final EnumOptionData interestCalculationType,
            final EnumOptionData interestCalculationDaysInYearType, final BigDecimal minRequiredOpeningBalance,
            final Integer lockinPeriodFrequency, final EnumOptionData lockinPeriodFrequencyType, final boolean withdrawalFeeForTransfers,
            final EnumOptionData accountingType, final boolean allowOverdraft, final BigDecimal overdraftLimit,
            final BigDecimal minRequiredBalance, final boolean enforceMinRequiredBalance, final BigDecimal minBalanceForInterestCalculation,
            final BigDecimal nominalAnnualInterestRateOverdraft, final BigDecimal minOverdraftForInterestCalculation,
            final LocalDate startDate, final LocalDate closeDate, final String status, final CodeValueData productGroup) {

        final Map<String, Object> accountingMappings = null;
        final Collection<PaymentTypeToGLAccountMapper> paymentChannelToFundSourceMappings = null;

        final Collection<CurrencyData> currencyOptions = null;
        final Collection<EnumOptionData> interestCompoundingPeriodTypeOptions = null;
        final Collection<EnumOptionData> interestPostingPeriodTypeOptions = null;
        final Collection<EnumOptionData> interestCalculationTypeOptions = null;
        final Collection<EnumOptionData> interestCalculationDaysInYearTypeOptions = null;
        final Collection<EnumOptionData> lockinPeriodFrequencyTypeOptions = null;
        final Collection<EnumOptionData> withdrawalFeeTypeOptions = null;
        final Collection<PaymentTypeData> paymentTypeOptions = null;
        final Collection<EnumOptionData> accountingRuleOptions = null;
        final Map<String, List<GLAccountData>> accountingMappingOptions = null;
        final Collection<ChargeData> chargeOptions = null;
        final Collection<ChargeData> penaltyOptions = null;
        final Collection<ChargeData> charges = null;
        final Collection<ChargeToGLAccountMapper> feeToIncomeAccountMappings = null;
        final Collection<ChargeToGLAccountMapper> penaltyToIncomeAccountMappings = null;
        final Collection<CodeValueData> productGroupOptions = null;
        final Collection<InterestRateCharts> interestRateCharts = null;
        final  Collection<ApplyProductChargeToExistingSavingsAccountData> applyProductChargeToExistingSavingsAccount = null;


        return new SavingsProductData(id, name, shortName, description, currency, nominalAnnualInterestRate, interestCompoundingPeriodType,
                interestPostingPeriodType, interestCalculationType, interestCalculationDaysInYearType, minRequiredOpeningBalance,
                lockinPeriodFrequency, lockinPeriodFrequencyType, withdrawalFeeForTransfers, accountingType, accountingMappings,
                paymentChannelToFundSourceMappings, productGroupOptions, currencyOptions, interestCompoundingPeriodTypeOptions,
                interestPostingPeriodTypeOptions, interestCalculationTypeOptions, interestCalculationDaysInYearTypeOptions,
                lockinPeriodFrequencyTypeOptions, withdrawalFeeTypeOptions, paymentTypeOptions, accountingRuleOptions,
                accountingMappingOptions, charges, chargeOptions, penaltyOptions, feeToIncomeAccountMappings,
                penaltyToIncomeAccountMappings, allowOverdraft, overdraftLimit, minRequiredBalance, enforceMinRequiredBalance,
                minBalanceForInterestCalculation, nominalAnnualInterestRateOverdraft, minOverdraftForInterestCalculation,
                startDate, closeDate, status, productGroup,interestRateCharts,applyProductChargeToExistingSavingsAccount);
    }

    public static SavingsProductData lookup(final Long id, final String name) {

        final String shortName = null;
        final CurrencyData currency = null;
        final String description = null;
        final BigDecimal nominalAnnualInterestRate = null;
        final EnumOptionData interestCompoundingPeriodType = null;
        final EnumOptionData interestPostingPeriodType = null;
        final EnumOptionData interestCalculationType = null;
        final EnumOptionData interestCalculationDaysInYearType = null;
        final BigDecimal minRequiredOpeningBalance = null;
        final Integer lockinPeriodFrequency = null;
        final EnumOptionData lockinPeriodFrequencyType = null;
        final boolean withdrawalFeeForTransfers = false;
        final EnumOptionData accountingType = null;
        final Map<String, Object> accountingMappings = null;
        final Collection<PaymentTypeToGLAccountMapper> paymentChannelToFundSourceMappings = null;
        final boolean allowOverdraft = false;
        final BigDecimal overdraftLimit = null;
        final BigDecimal nominalAnnualInterestRateOverdraft = null;
        final BigDecimal minOverdraftForInterestCalculation = null;
        final BigDecimal minRequiredBalance = null;
        final boolean enforceMinRequiredBalance = false;
        final BigDecimal minBalanceForInterestCalculation = null;
        final LocalDate startDate = null;
        final LocalDate closeDate = null;
        final String status = null;
        final CodeValueData productGroup = null;

        final Collection<CurrencyData> currencyOptions = null;
        final Collection<EnumOptionData> interestCompoundingPeriodTypeOptions = null;
        final Collection<EnumOptionData> interestPostingPeriodTypeOptions = null;
        final Collection<EnumOptionData> interestCalculationTypeOptions = null;
        final Collection<EnumOptionData> interestCalculationDaysInYearTypeOptions = null;
        final Collection<EnumOptionData> lockinPeriodFrequencyTypeOptions = null;
        final Collection<EnumOptionData> withdrawalFeeTypeOptions = null;
        final Collection<PaymentTypeData> paymentTypeOptions = null;
        final Collection<EnumOptionData> accountingRuleOptions = null;
        final Map<String, List<GLAccountData>> accountingMappingOptions = null;
        final Collection<ChargeData> charges = null;
        final Collection<ChargeData> chargeOptions = null;
        final Collection<ChargeData> penaltyOptions = null;
        final Collection<ChargeToGLAccountMapper> feeToIncomeAccountMappings = null;
        final Collection<ChargeToGLAccountMapper> penaltyToIncomeAccountMappings = null;
        final Collection<CodeValueData> productGroupOptions = null;
        final Collection<InterestRateCharts> interestRateCharts = null;
        final Collection<ApplyProductChargeToExistingSavingsAccountData> applyProductChargeToExistingSavingsAccount = null;

        return new SavingsProductData(id, name, shortName, description, currency, nominalAnnualInterestRate, interestCompoundingPeriodType,
                interestPostingPeriodType, interestCalculationType, interestCalculationDaysInYearType, minRequiredOpeningBalance,
                lockinPeriodFrequency, lockinPeriodFrequencyType, withdrawalFeeForTransfers, accountingType, accountingMappings,
                paymentChannelToFundSourceMappings, productGroupOptions, currencyOptions, interestCompoundingPeriodTypeOptions,
                interestPostingPeriodTypeOptions, interestCalculationTypeOptions, interestCalculationDaysInYearTypeOptions,
                lockinPeriodFrequencyTypeOptions, withdrawalFeeTypeOptions, paymentTypeOptions, accountingRuleOptions,
                accountingMappingOptions, charges, chargeOptions, penaltyOptions, feeToIncomeAccountMappings,
                penaltyToIncomeAccountMappings, allowOverdraft, overdraftLimit, minRequiredBalance, enforceMinRequiredBalance,
                minBalanceForInterestCalculation, nominalAnnualInterestRateOverdraft, minOverdraftForInterestCalculation,
                startDate, closeDate, status, productGroup,interestRateCharts,applyProductChargeToExistingSavingsAccount);
    }
    public static SavingsProductData withInterestRateCharts(final SavingsProductData product,final Collection<InterestRateCharts> interestRateCharts){

        return new SavingsProductData(product.id, product.name, product.shortName, product.description, product.currency,
                product.nominalAnnualInterestRate, product.interestCompoundingPeriodType, product.interestPostingPeriodType,
                product.interestCalculationType, product.interestCalculationDaysInYearType, product.minRequiredOpeningBalance,
                product.lockinPeriodFrequency, product.lockinPeriodFrequencyType, product.withdrawalFeeForTransfers,
                product.accountingRule, product.accountingMappings, product.paymentChannelToFundSourceMappings,
                product.productGroupOptions, product.currencyOptions,
                product.interestCompoundingPeriodTypeOptions, product.interestPostingPeriodTypeOptions,
                product.interestCalculationTypeOptions, product.interestCalculationDaysInYearTypeOptions,
                product.lockinPeriodFrequencyTypeOptions, product.withdrawalFeeTypeOptions, product.paymentTypeOptions,
                product.accountingRuleOptions, product.accountingMappingOptions, product.charges, product.chargeOptions, product.penaltyOptions,
                product.feeToIncomeAccountMappings, product.penaltyToIncomeAccountMappings, product.allowOverdraft, product.overdraftLimit,
                product.minRequiredBalance, product.enforceMinRequiredBalance, product.minBalanceForInterestCalculation,
                product.nominalAnnualInterestRateOverdraft, product.minOverdraftForInterestCalculation, product.startDate,
                product.closeDate, product.status, product.productGroup,interestRateCharts,null);
    }
    public static SavingsProductData withProductChargeToExistingAccounts(final SavingsProductData product,final Collection<ApplyProductChargeToExistingSavingsAccountData> applyProductChargeToExistingSavingsAccount){

        return new SavingsProductData(product.id, product.name, product.shortName, product.description, product.currency,
                product.nominalAnnualInterestRate, product.interestCompoundingPeriodType, product.interestPostingPeriodType,
                product.interestCalculationType, product.interestCalculationDaysInYearType, product.minRequiredOpeningBalance,
                product.lockinPeriodFrequency, product.lockinPeriodFrequencyType, product.withdrawalFeeForTransfers,
                product.accountingRule, product.accountingMappings, product.paymentChannelToFundSourceMappings,
                product.productGroupOptions, product.currencyOptions,
                product.interestCompoundingPeriodTypeOptions, product.interestPostingPeriodTypeOptions,
                product.interestCalculationTypeOptions, product.interestCalculationDaysInYearTypeOptions,
                product.lockinPeriodFrequencyTypeOptions, product.withdrawalFeeTypeOptions, product.paymentTypeOptions,
                product.accountingRuleOptions, product.accountingMappingOptions, product.charges, product.chargeOptions, product.penaltyOptions,
                product.feeToIncomeAccountMappings, product.penaltyToIncomeAccountMappings, product.allowOverdraft, product.overdraftLimit,
                product.minRequiredBalance, product.enforceMinRequiredBalance, product.minBalanceForInterestCalculation,
                product.nominalAnnualInterestRateOverdraft, product.minOverdraftForInterestCalculation, product.startDate,
                product.closeDate, product.status, product.productGroup,product.interestRateCharts, applyProductChargeToExistingSavingsAccount);
    }


    private SavingsProductData(final Long id, final String name, final String shortName, final String description,
            final CurrencyData currency, final BigDecimal nominalAnnualInterestRate, final EnumOptionData interestCompoundingPeriodType,
            final EnumOptionData interestPostingPeriodType, final EnumOptionData interestCalculationType,
            final EnumOptionData interestCalculationDaysInYearType, final BigDecimal minRequiredOpeningBalance,
            final Integer lockinPeriodFrequency, final EnumOptionData lockinPeriodFrequencyType, final boolean withdrawalFeeForTransfers,
            final EnumOptionData accountingType, final Map<String, Object> accountingMappings,
            final Collection<PaymentTypeToGLAccountMapper> paymentChannelToFundSourceMappings,
            final Collection<CodeValueData> productGroupOptions,
            final Collection<CurrencyData> currencyOptions, final Collection<EnumOptionData> interestCompoundingPeriodTypeOptions,
            final Collection<EnumOptionData> interestPostingPeriodTypeOptions,
            final Collection<EnumOptionData> interestCalculationTypeOptions,
            final Collection<EnumOptionData> interestCalculationDaysInYearTypeOptions,
            final Collection<EnumOptionData> lockinPeriodFrequencyTypeOptions, final Collection<EnumOptionData> withdrawalFeeTypeOptions,
            final Collection<PaymentTypeData> paymentTypeOptions, final Collection<EnumOptionData> accountingRuleOptions,
            final Map<String, List<GLAccountData>> accountingMappingOptions, final Collection<ChargeData> charges,
            final Collection<ChargeData> chargeOptions, final Collection<ChargeData> penaltyOptions,
            final Collection<ChargeToGLAccountMapper> feeToIncomeAccountMappings,
            final Collection<ChargeToGLAccountMapper> penaltyToIncomeAccountMappings, final boolean allowOverdraft,
            final BigDecimal overdraftLimit, final BigDecimal minRequiredBalance, final boolean enforceMinRequiredBalance,
            final BigDecimal minBalanceForInterestCalculation,
            final BigDecimal nominalAnnualInterestRateOverdraft, final BigDecimal minOverdraftForInterestCalculation,
            final LocalDate startDate, final LocalDate closeDate, final String status, final CodeValueData productGroup,
            final Collection<InterestRateCharts> interestRateCharts, final Collection<ApplyProductChargeToExistingSavingsAccountData> productChargeToExistingSavingsAccounts) {
        this.id = id;
        this.name = name;
        this.shortName = shortName;
        this.description = description;
        this.currency = currency;
        this.nominalAnnualInterestRate = nominalAnnualInterestRate;
        this.interestCompoundingPeriodType = interestCompoundingPeriodType;
        this.interestPostingPeriodType = interestPostingPeriodType;
        this.interestCalculationType = interestCalculationType;
        this.interestCalculationDaysInYearType = interestCalculationDaysInYearType;
        this.accountingRule = accountingType;
        this.minRequiredOpeningBalance = minRequiredOpeningBalance;
        this.lockinPeriodFrequency = lockinPeriodFrequency;
        this.lockinPeriodFrequencyType = lockinPeriodFrequencyType;
        this.withdrawalFeeForTransfers = withdrawalFeeForTransfers;
        this.startDate = startDate;
        this.closeDate = closeDate;
        this.status = status;
        this.productGroup = productGroup;

        this.currencyOptions = currencyOptions;
        this.interestCompoundingPeriodTypeOptions = interestCompoundingPeriodTypeOptions;
        this.interestPostingPeriodTypeOptions = interestPostingPeriodTypeOptions;
        this.interestCalculationTypeOptions = interestCalculationTypeOptions;
        this.interestCalculationDaysInYearTypeOptions = interestCalculationDaysInYearTypeOptions;
        this.lockinPeriodFrequencyTypeOptions = lockinPeriodFrequencyTypeOptions;
        this.withdrawalFeeTypeOptions = withdrawalFeeTypeOptions;
        this.productGroupOptions = productGroupOptions;

        this.paymentTypeOptions = paymentTypeOptions;
        this.accountingMappingOptions = accountingMappingOptions;
        this.accountingRuleOptions = accountingRuleOptions;
        if (accountingMappings == null || accountingMappings.isEmpty()) {
            this.accountingMappings = null;
        } else {
            this.accountingMappings = accountingMappings;
        }
        this.paymentChannelToFundSourceMappings = paymentChannelToFundSourceMappings;

        this.charges = charges;// charges associated with Savings product
        this.chargeOptions = chargeOptions;// charges available for adding to
                                           // Savings product
        this.penaltyOptions = penaltyOptions;// penalties available for adding
                                             // to Savings product

        this.feeToIncomeAccountMappings = feeToIncomeAccountMappings;
        this.penaltyToIncomeAccountMappings = penaltyToIncomeAccountMappings;
        this.allowOverdraft = allowOverdraft;
        this.overdraftLimit = overdraftLimit;
        this.minRequiredBalance = minRequiredBalance;
        this.enforceMinRequiredBalance = enforceMinRequiredBalance;
        this.minBalanceForInterestCalculation = minBalanceForInterestCalculation;
        this.nominalAnnualInterestRateOverdraft = nominalAnnualInterestRateOverdraft;
        this.minOverdraftForInterestCalculation = minOverdraftForInterestCalculation;
        this.interestRateCharts = interestRateCharts;
        this.applyProductChargeToExistingSavingsAccount =  productChargeToExistingSavingsAccounts;
    }

    public boolean hasAccountingEnabled() {
        return this.accountingRule.getId() > AccountingRuleType.NONE.getValue();
    }

    public int accountingRuleTypeId() {
        return this.accountingRule.getId().intValue();
    }

    @Override
    public boolean equals(final Object obj) {
        final SavingsProductData productData = (SavingsProductData) obj;
        return productData.id.compareTo(this.id) == 0;
    }

    @Override
    public int hashCode() {
        return this.id.hashCode();
    }

    public String getName() {
        return this.name;
    }
}