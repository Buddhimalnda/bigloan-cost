package com.bigloan.app.opration

import android.content.Context
import android.widget.Toast

fun showToster(message: String, context: Context) {
    val toast = Toast.makeText(context, message, Toast.LENGTH_SHORT)
    toast.show()
}