package com.bigloan.app.module

import java.util.Date

class Group {
    private var name: String? = null
    private var owner: String? = null
    private var members: List<String>? = null
    private var passcode: String? = null
    private var createAt: Date? = null
    private var updateAt: List<Date>? = null

    constructor(name: String, owner: String, members: List<String>, passcode: String) {
        this.name = name
        this.owner = owner
        this.members = members
        this.passcode = passcode
        this.createAt = Date()
        this.updateAt = listOf(Date())
    }

    constructor(name: String?, passcode: String?) {
        this.name = name
        this.passcode = passcode
    }

    fun getName(): String? {
        return name
    }

    fun setName(name: String) {
        this.name = name
    }

    fun getOwner(): String? {
        return owner
    }

    fun setOwner(owner: String) {
        this.owner = owner
    }

    fun getMembers(): List<String>? {
        return members
    }

    fun setMembers(members: List<String>) {
        this.members = members
    }

    fun getPasscode(): String? {
        return passcode
    }


    fun setPasscode(passcode: String) {
        this.passcode = passcode
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

    fun addMember(member: String) {
        members?.toMutableList()?.add(member)
        updateAt?.toMutableList()?.add(Date())
    }

    fun removeMember(member: String) {
        members?.toMutableList()?.remove(member)
        updateAt?.toMutableList()?.add(Date())
    }

    fun updatePasscode(passcode: String) {
        this.passcode = passcode
        updateAt?.toMutableList()?.add(Date())
    }

    fun updateName(name: String) {
        this.name = name
        updateAt?.toMutableList()?.add(Date())
    }

    fun updateOwner(owner: String) {
        this.owner = owner
        updateAt?.toMutableList()?.add(Date())
    }

    fun updateMembers(members: List<String>) {
        this.members = members
        updateAt?.toMutableList()?.add(Date())
    }


}