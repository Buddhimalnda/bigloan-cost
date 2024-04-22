package com.bigloan.app.module

import java.util.Date

class Subscription {
    private var subscriptionId: String? = null
    private var subscriptionType: String? = null
    private var subscriptionStatus: String? = null
    private var subscriptionDuration: Int? = null
    private var subscriptionDescription: String? = null
    private var subscriptionPrice: Double? = null
    private var createAt: Date? = null
    private var updateAt: List<Date>? = null

    constructor(
        subscriptionId: String, subscriptionType: String, subscriptionStatus: String,
        subscriptionDuration: Int, subscriptionDescription: String, subscriptionPrice: Double
    ) {
        this.subscriptionId = subscriptionId
        this.subscriptionType = subscriptionType
        this.subscriptionStatus = subscriptionStatus
        this.subscriptionDuration = subscriptionDuration
        this.subscriptionDescription = subscriptionDescription
        this.subscriptionPrice = subscriptionPrice
        this.createAt = Date()
        this.updateAt = listOf(Date())
    }

    constructor(subscriptionId: String?) {
        this.subscriptionId = subscriptionId
        // get subscription details from database
    }

    fun getSubscriptionId(): String? {
        return subscriptionId
    }

    fun setSubscriptionId(subscriptionId: String) {
        this.subscriptionId = subscriptionId
    }

    fun getSubscriptionType(): String? {
        return subscriptionType
    }

    fun setSubscriptionType(subscriptionType: String) {
        this.subscriptionType = subscriptionType
    }

    fun getSubscriptionStatus(): String? {
        return subscriptionStatus
    }

    fun setSubscriptionStatus(subscriptionStatus: String) {
        this.subscriptionStatus = subscriptionStatus
    }

    fun getSubscriptionDuration(): Int? {
        return subscriptionDuration
    }

    fun setSubscriptionDuration(subscriptionDuration: Int) {
        this.subscriptionDuration = subscriptionDuration
    }

    fun getSubscriptionDescription(): String? {
        return subscriptionDescription
    }

    fun setSubscriptionDescription(subscriptionDescription: String) {
        this.subscriptionDescription = subscriptionDescription
    }

    fun getSubscriptionPrice(): Double? {
        return subscriptionPrice
    }

    fun setSubscriptionPrice(subscriptionPrice: Double) {
        this.subscriptionPrice = subscriptionPrice
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
    

    fun createSubscription() {
        // create subscription
    }

    fun updateSubscription() {
        // update subscription
    }

    fun deleteSubscription() {
        // delete subscription
    }


}