package com.bigloan.app.module

import android.content.Context
import android.widget.Toast


class User {
    private var name: String? = null
    private var email: String? = null
    private var mobile: String? = null
    private var nic: String? = null
    private var password: String? = null
    private var address: String? = null
    private var city: String? = null
    private var state: String? = null
    private var pincode: String? = null
    private var dob: String? = null

    private lateinit var db: DatabaseHandler

    constructor(email: String, password: String) {
        this.email = email
        this.password = password
        // get user details from database
    }

    constructor(
        name: String, email: String, mobile: String, nic: String, password: String,
        address: String, city: String, state: String, pincode: String, dob: String,
    ) {
        this.name = name
        this.email = email
        this.mobile = mobile
        this.password = password
        this.address = address
        this.city = city
        this.state = state
        this.pincode = pincode
        this.dob = dob
        this.nic = nic
    }

    constructor(data: String, pincode: String, isEmail: Boolean) {
        if (isEmail) {
            this.email = data
        } else {
            this.mobile = data
        }
        this.pincode = pincode
        // get user details from database
    }

    fun getName(): String? {
        return name
    }

    fun setName(name: String) {
        this.name = name
    }

    fun getEmail(): String? {
        return email
    }

    fun setEmail(email: String) {
        this.email = email
    }

    fun getMobile(): String? {
        return mobile
    }

    fun setMobile(mobile: String) {
        this.mobile = mobile
    }

    fun getPassword(): String? {
        return password
    }

    fun setPassword(password: String) {
        this.password = password
    }

    fun getAddress(): String? {
        return address
    }

    fun setAddress(address: String) {
        this.address = address
    }

    fun getCity(): String? {
        return city
    }

    fun setCity(city: String) {
        this.city = city
    }

    fun getState(): String? {
        return state
    }

    fun setState(state: String) {
        this.state = state
    }

    fun getPincode(): String? {
        return pincode
    }

    fun setPincode(pincode: String) {
        this.pincode = pincode
    }

    fun getDob(): String? {
        return dob
    }

    fun setDob(dob: String) {
        this.dob = dob
    }

    fun getNic(): String? {
        return nic
    }

    fun setNic(nic: String) {
        this.nic = nic
    }

    fun login(email: String, password: String): User {
        val x: AlertMzg<User?> = db.login(email, password)
        showToster(x.getMessage().toString())
        val session: Session = Session("LOGIN", "login", "active", 60, "User login session")
        return x.getNext() as User
    }

    fun showToster(message: String) {
        val toast = Toast.makeText(this as Context, message, Toast.LENGTH_SHORT)
        toast.show()
    }

    fun register(): User? {
        // insert user details into database
        val x: AlertMzg<User> = db.addUser(this)
        showToster(x.getMessage().toString())
        return x.getNext()
    }

    fun update(): User? {
        // update user details into database
        val x: AlertMzg<User> = db.updateUser(this)
        showToster(x.getMessage().toString())
        return x.getNext()
    }

    fun delete() {
        // delete user details from database
        val x: AlertMzg<Boolean> = db.deleteUser(getEmail().toString())
        showToster(x.getMessage().toString())
    }


}
