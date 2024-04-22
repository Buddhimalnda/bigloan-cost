package com.bigloan.app.module

class AlertMzg<X> {
    private var title: String? = null
    private var message: String? = null
    private var next: X? = null

    constructor(title: String, message: String, next: X) {
        this.title = title
        this.message = message
        this.next = next
    }

    fun getTitle(): String? {
        return title
    }

    fun setTitle(title: String) {
        this.title = title
    }

    fun getMessage(): String? {
        return message
    }

    fun setMessage(message: String) {
        this.message = message
    }

    fun getNext(): X? {
        return next
    }

    fun setNext(next: X) {
        this.next = next
    }

    fun show() {
        println("{Title: $title},{Message: $message}")
    }
}