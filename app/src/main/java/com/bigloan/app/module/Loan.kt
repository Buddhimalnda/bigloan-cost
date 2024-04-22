package com.bigloan.app.module

import java.util.Date

class Loan {
    private var loanAmount: Double? = null
    private var interestRate: Double? = null
    private var loanId: String? = null
    private var loanType: String? = null
    private var loanStatus: String? = null
    private var loanDuration: Int? = null
    private var loanDate: String? = null
    private var loanEndDate: String? = null
    private var loanFrom: String? = null
    private var loanTo: String? = null
    private var createAt: Date? = null
    private var updateAt: List<Date>? = null

    constructor(
        loanAmount: Double, interestRate: Double, loanId: String, loanType: String,
        loanStatus: String, loanDuration: Int, loanDate: String, loanEndDate: String,
        loanFrom: String, loanTo: String
    ) {
        this.loanAmount = loanAmount
        this.interestRate = interestRate
        this.loanId = loanId
        this.loanType = loanType
        this.loanStatus = loanStatus
        this.loanDuration = loanDuration
        this.loanDate = loanDate
        this.loanEndDate = loanEndDate
        this.loanFrom = loanFrom
        this.loanTo = loanTo
    }

    constructor(loanId: String?) {
        this.loanId = loanId
        // get loan details from database
    }

    fun getLoanAmount(): Double? {
        return loanAmount
    }

    fun setLoanAmount(loanAmount: Double) {
        this.loanAmount = loanAmount
    }

    fun getInterestRate(): Double? {
        return interestRate
    }

    fun setInterestRate(interestRate: Double) {
        this.interestRate = interestRate
    }

    fun getLoanId(): String? {
        return loanId
    }

    fun setLoanId(loanId: String) {
        this.loanId = loanId
    }

    fun getLoanType(): String? {
        return loanType
    }

    fun setLoanType(loanType: String) {
        this.loanType = loanType
    }

    fun getLoanStatus(): String? {
        return loanStatus
    }

    fun setLoanStatus(loanStatus: String) {
        this.loanStatus = loanStatus
    }

    fun getLoanDuration(): Int? {
        return loanDuration
    }

    fun setLoanDuration(loanDuration: Int) {
        this.loanDuration = loanDuration
    }

    fun getLoanDate(): String? {
        return loanDate
    }

    fun setLoanDate(loanDate: String) {
        this.loanDate = loanDate
    }

    fun getLoanEndDate(): String? {
        return loanEndDate
    }

    fun setLoanEndDate(loanEndDate: String) {
        this.loanEndDate = loanEndDate
    }

    fun getLoanFrom(): String? {
        return loanFrom
    }

    fun setLoanFrom(loanFrom: String) {
        this.loanFrom = loanFrom
    }

    fun getLoanTo(): String? {
        return loanTo
    }

    fun setLoanTo(loanTo: String) {
        this.loanTo = loanTo
    }

    fun getCreateAt(): Date? {
        return createAt
    }

    fun setCreateAt(createAt: Date) {
        this.createAt = createAt
    }

    fun getUpdateAt(): List<Date>? {
        return updateAt
    }

    fun setUpdateAt(updateAt: List<Date>) {
        this.updateAt = updateAt
    }

    fun createLoan() {
        // create loan
    }

    fun deleteLoan() {
        // delete loan
        setLoanStatus("deleted");
    }

    fun updateLoan() {
        // update loan
    }

    fun getLoanDetails() {
        // get loan details
    }

}