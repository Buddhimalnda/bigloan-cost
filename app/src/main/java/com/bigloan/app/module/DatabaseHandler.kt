package com.bigloan.app.module

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import java.lang.Exception

class DatabaseHandler(context: Context) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private val DATABASE_VERSION = 1
        private val DATABASE_NAME = "BigLoan"
        private val TABLE_USER = "User"
        private val TABLE_GROUP = "GroupTable"
        private val TABLE_LOAN = "Loan"
        private val TABLE_SUBSCRIPTION = "Subscription"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        val CREATE_USER_TABLE = ("CREATE TABLE " + TABLE_USER + "("
                + "id INTEGER PRIMARY KEY,"
                + "name TEXT,"
                + "email TEXT,"
                + "mobile TEXT,"
                + "password TEXT,"
                + "address TEXT,"
                + "city TEXT,"
                + "state TEXT,"
                + "pincode TEXT,"
                + "dob TEXT"
                + ")")
        db?.execSQL(CREATE_USER_TABLE)

        val CREATE_GROUP_TABLE = ("CREATE TABLE " + TABLE_GROUP + "("
                + "id INTEGER PRIMARY KEY,"
                + "name TEXT,"
                + "owner TEXT,"
                + "members TEXT,"
                + "passcode TEXT,"
                + "createAt TEXT,"
                + "updateAt TEXT"
                + ")")
        db?.execSQL(CREATE_GROUP_TABLE)

        val CREATE_LOAN_TABLE = ("CREATE TABLE " + TABLE_LOAN + "("
                + "id INTEGER PRIMARY KEY,"
                + "loanAmount REAL,"
                + "interestRate REAL,"
                + "loanId TEXT,"
                + "loanType TEXT,"
                + "loanStatus TEXT,"
                + "loanDuration INTEGER,"
                + "loanDate TEXT,"
                + "loanEndDate TEXT,"
                + "loanFrom TEXT,"
                + "loanTo TEXT,"
                + "createAt TEXT,"
                + "updateAt TEXT"
                + ")")
        db?.execSQL(CREATE_LOAN_TABLE)

        val CREATE_SUBSCRIPTION_TABLE = ("CREATE TABLE " + TABLE_SUBSCRIPTION + "("
                + "id INTEGER PRIMARY KEY,"
                + "subscriptionId TEXT,"
                + "subscriptionType TEXT,"
                + "subscriptionStatus TEXT,"
                + "subscriptionDuration INTEGER,"
                + "subscriptionDescription TEXT,"
                + "subscriptionPrice REAL,"
                + "createAt TEXT,"
                + "updateAt TEXT"
                + ")")
        db?.execSQL(CREATE_SUBSCRIPTION_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS $TABLE_USER")
        db?.execSQL("DROP TABLE IF EXISTS $TABLE_GROUP")
        db?.execSQL("DROP TABLE IF EXISTS $TABLE_LOAN")
        db?.execSQL("DROP TABLE IF EXISTS $TABLE_SUBSCRIPTION")
        onCreate(db)
    }

    // curd operations
    fun addUser(user: User): AlertMzg<User> {
        val db = this.writableDatabase
        val values = ContentValues()
        values.put("name", user.getName())
        values.put("email", user.getEmail())
        values.put("mobile", user.getMobile())
        values.put("password", user.getPassword())
        values.put("address", user.getAddress())
        values.put("city", user.getCity())
        values.put("state", user.getState())
        values.put("pincode", user.getPincode())
        values.put("dob", user.getDob())
        db.insert(TABLE_USER, null, values)
        db.close()
        return AlertMzg("SUCCESS", "User added successfully", user)
    }

    fun getUser(email: String): AlertMzg<User> {
        val db = this.readableDatabase
        val cursor = db.query(
            TABLE_USER,
            arrayOf(
                "id",
                "name",
                "email",
                "mobile",
                "nic",
                "password",
                "address",
                "city",
                "state",
                "pincode",
                "dob"
            ),
            "email = ? ",
            arrayOf(email),
            null,
            null,
            null,
            null
        )
        cursor?.moveToFirst()
        val user = User(
            cursor.getString(1),
            cursor.getString(2),
            cursor.getString(3),
            cursor.getString(4),
            cursor.getString(5),
            cursor.getString(6),
            cursor.getString(7),
            cursor.getString(8),
            cursor.getString(9),
            cursor.getString(10)
        )
        return AlertMzg("SUCCESS", "User found", user)
    }

    fun login(email: String, passwd: String): AlertMzg<User?> {
        val db = this.readableDatabase
        try {

            val cursor = db.query(
                TABLE_USER,
                arrayOf(
                    "id",
                    "name",
                    "email",
                    "mobile",
                    "password",
                    "address",
                    "city",
                    "state",
                    "pincode",
                    "dob"
                ),
                "email = ? AND password = ?",
                arrayOf(email, passwd),
                null,
                null,
                null,
                null
            )
            cursor?.moveToFirst()
            val user = User(
                cursor.getString(1),
                cursor.getString(2),
                cursor.getString(3),
                cursor.getString(4),
                cursor.getString(5),
                cursor.getString(6),
                cursor.getString(7),
                cursor.getString(8),
                cursor.getString(9),
                cursor.getString(10)
            )
            return AlertMzg("SUCCESS", "User found", user)
        } catch (e: Exception) {
            return AlertMzg("ERROR", "User not found", null)
        }
    }

    fun updateUser(user: User): AlertMzg<User> {
        val db = this.writableDatabase
        val values = ContentValues()
        values.put("name", user.getName())
        values.put("email", user.getEmail())
        values.put("mobile", user.getMobile())
        values.put("password", user.getPassword())
        values.put("address", user.getAddress())
        values.put("city", user.getCity())
        values.put("state", user.getState())
        values.put("pincode", user.getPincode())
        values.put("dob", user.getDob())
        db.update(TABLE_USER, values, "email = ?", arrayOf(user.getEmail()))
        db.close()
        return AlertMzg("SUCCESS", "User updated successfully", user)
    }

    fun deleteUser(email: String): AlertMzg<Boolean> {
        val db = this.writableDatabase
        db.delete(TABLE_USER, "email = ?", arrayOf(email))
        db.close()
        return AlertMzg("SUCCESS", "User deleted successfully", true)
    }

    fun addGroup(group: Group): AlertMzg<Group> {
        val db = this.writableDatabase
        val values = ContentValues()
        values.put("name", group.getName())
        values.put("owner", group.getOwner())
        values.put("members", group.getMembers().toString())
        values.put("passcode", group.getPasscode())
        values.put("createAt", group.getCreateAt().toString())
        values.put("updateAt", group.getUpdateAt().toString())
        db.insert(TABLE_GROUP, null, values)
        db.close()
        return AlertMzg("SUCCESS", "Group added successfully", group)
    }

    fun getGroup(name: String): AlertMzg<Group> {
        val db = this.readableDatabase
        val cursor = db.query(
            TABLE_GROUP,
            arrayOf(
                "id",
                "name",
                "owner",
                "members",
                "passcode",
                "createAt",
                "updateAt"
            ),
            "name = ? ",
            arrayOf(name),
            null,
            null,
            null,
            null
        )
        cursor?.moveToFirst()
        val group = Group(
            cursor.getString(1),
            cursor.getString(2),
            cursor.getString(3).split(","),
            cursor.getString(4)
        )
        return AlertMzg("SUCCESS", "Group found", group)
    }

    fun addLoan(loan: Loan): AlertMzg<Loan> {
        val db = this.writableDatabase
        val values = ContentValues()
        values.put("loanAmount", loan.getLoanAmount())
        values.put("interestRate", loan.getInterestRate())
        values.put("loanId", loan.getLoanId())
        values.put("loanType", loan.getLoanType())
        values.put("loanStatus", loan.getLoanStatus())
        values.put("loanDuration", loan.getLoanDuration())
        values.put("loanDate", loan.getLoanDate())
        values.put("loanEndDate", loan.getLoanEndDate())
        values.put("loanFrom", loan.getLoanFrom())
        values.put("loanTo", loan.getLoanTo())
        values.put("createAt", loan.getCreateAt().toString())
        values.put("updateAt", loan.getUpdateAt().toString())
        db.insert(TABLE_LOAN, null, values)
        db.close()
        return AlertMzg("SUCCESS", "Loan added successfully", loan)
    }

    fun getLoan(loanId: String): AlertMzg<Loan> {
        val db = this.readableDatabase
        val cursor = db.query(
            TABLE_LOAN,
            arrayOf(
                "id",
                "loanAmount",
                "interestRate",
                "loanId",
                "loanType",
                "loanStatus",
                "loanDuration",
                "loanDate",
                "loanEndDate",
                "loanFrom",
                "loanTo",
                "createAt",
                "updateAt"
            ),
            "loanId = ? ",
            arrayOf(loanId),
            null,
            null,
            null,
            null
        )
        cursor?.moveToFirst()
        val loan = Loan(
            cursor.getDouble(1),
            cursor.getDouble(2),
            cursor.getString(3),
            cursor.getString(4),
            cursor.getString(5),
            cursor.getInt(6),
            cursor.getString(7),
            cursor.getString(8),
            cursor.getString(9),
            cursor.getString(10)
        )
        return AlertMzg("SUCCESS", "Loan found", loan)
    }

    fun addSubscription(subscription: Subscription): AlertMzg<Subscription> {
        val db = this.writableDatabase
        val values = ContentValues()
        values.put("subscriptionId", subscription.getSubscriptionId())
        values.put("subscriptionType", subscription.getSubscriptionType())
        values.put("subscriptionStatus", subscription.getSubscriptionStatus())
        values.put("subscriptionDuration", subscription.getSubscriptionDuration())
        values.put("subscriptionDescription", subscription.getSubscriptionDescription())
        values.put("subscriptionPrice", subscription.getSubscriptionPrice())
        values.put("createAt", subscription.getCreateAt().toString())
        values.put("updateAt", subscription.getUpdateAt().toString())
        db.insert(TABLE_SUBSCRIPTION, null, values)
        db.close()
        return AlertMzg("SUCCESS", "Subscription added successfully", subscription)
    }

    fun getSubscription(subscriptionId: String): AlertMzg<Subscription> {
        val db = this.readableDatabase
        val cursor = db.query(
            TABLE_SUBSCRIPTION,
            arrayOf(
                "id",
                "subscriptionId",
                "subscriptionType",
                "subscriptionStatus",
                "subscriptionDuration",
                "subscriptionDescription",
                "subscriptionPrice",
                "createAt",
                "updateAt"
            ),
            "subscriptionId = ? ",
            arrayOf(subscriptionId),
            null,
            null,
            null,
            null
        )
        cursor?.moveToFirst()
        val subscription = Subscription(
            cursor.getString(1),
            cursor.getString(2),
            cursor.getString(3),
            cursor.getInt(4),
            cursor.getString(5),
            cursor.getDouble(6)
        )
        return AlertMzg("SUCCESS", "Subscription found", subscription)
    }

    fun updateSubscription(subscription: Subscription): AlertMzg<Subscription> {
        val db = this.writableDatabase
        val values = ContentValues()
        values.put("subscriptionId", subscription.getSubscriptionId())
        values.put("subscriptionType", subscription.getSubscriptionType())
        values.put("subscriptionStatus", subscription.getSubscriptionStatus())
        values.put("subscriptionDuration", subscription.getSubscriptionDuration())
        values.put("subscriptionDescription", subscription.getSubscriptionDescription())
        values.put("subscriptionPrice", subscription.getSubscriptionPrice())
        values.put("createAt", subscription.getCreateAt().toString())
        values.put("updateAt", subscription.getUpdateAt().toString())
        db.update(
            TABLE_SUBSCRIPTION,
            values,
            "subscriptionId = ?",
            arrayOf(subscription.getSubscriptionId())
        )
        db.close()
        return AlertMzg("SUCCESS", "Subscription updated successfully", subscription)
    }

    fun deleteSubscription(subscriptionId: String): AlertMzg<Boolean> {
        val db = this.writableDatabase
        db.delete(TABLE_SUBSCRIPTION, "subscriptionId = ?", arrayOf(subscriptionId))
        db.close()
        return AlertMzg("SUCCESS", "Subscription deleted successfully", true)
    }

    fun deleteLoan(loanId: String): AlertMzg<Boolean> {
        val db = this.writableDatabase
        db.delete(TABLE_LOAN, "loanId = ?", arrayOf(loanId))
        db.close()
        return AlertMzg("SUCCESS", "Loan deleted successfully", true)
    }

    fun deleteGroup(name: String): AlertMzg<Boolean> {
        val db = this.writableDatabase
        db.delete(TABLE_GROUP, "name = ?", arrayOf(name))
        db.close()
        return AlertMzg("SUCCESS", "Group deleted successfully", true)
    }

    fun updateLoan(loan: Loan): AlertMzg<Loan> {
        val db = this.writableDatabase
        val values = ContentValues()
        values.put("loanAmount", loan.getLoanAmount())
        values.put("interestRate", loan.getInterestRate())
        values.put("loanId", loan.getLoanId())
        values.put("loanType", loan.getLoanType())
        values.put("loanStatus", loan.getLoanStatus())
        values.put("loanDuration", loan.getLoanDuration())
        values.put("loanDate", loan.getLoanDate())
        values.put("loanEndDate", loan.getLoanEndDate())
        values.put("loanFrom", loan.getLoanFrom())
        values.put("loanTo", loan.getLoanTo())
        values.put("createAt", loan.getCreateAt().toString())
        values.put("updateAt", loan.getUpdateAt().toString())
        db.update(TABLE_LOAN, values, "loanId = ?", arrayOf(loan.getLoanId()))
        db.close()
        return AlertMzg("SUCCESS", "Loan updated successfully", loan)
    }

    fun updateGroup(group: Group): AlertMzg<Group> {
        val db = this.writableDatabase
        val values = ContentValues()
        values.put("name", group.getName())
        values.put("owner", group.getOwner())
        values.put("members", group.getMembers().toString())
        values.put("passcode", group.getPasscode())
        values.put("createAt", group.getCreateAt().toString())
        values.put("updateAt", group.getUpdateAt().toString())
        db.update(TABLE_GROUP, values, "name = ?", arrayOf(group.getName()))
        db.close()
        return AlertMzg("SUCCESS", "Group updated successfully", group)
    }

    fun getAllUsers(): AlertMzg<List<User>> {
        val userList = ArrayList<User>()
        val db = this.readableDatabase
        val cursor = db.rawQuery("SELECT * FROM $TABLE_USER", null)
        if (cursor.moveToFirst()) {
            do {
                val user = User(
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getString(3),
                    cursor.getString(4),
                    cursor.getString(5),
                    cursor.getString(6),
                    cursor.getString(7),
                    cursor.getString(8),
                    cursor.getString(9),
                    cursor.getString(10)
                )
                userList.add(user)
            } while (cursor.moveToNext())
        }
        return AlertMzg("SUCCESS", "Users found", userList)
    }

    fun getAllGroups(): AlertMzg<List<Group>> {
        val groupList = ArrayList<Group>()
        val db = this.readableDatabase
        val cursor = db.rawQuery("SELECT * FROM $TABLE_GROUP", null)
        if (cursor.moveToFirst()) {
            do {
                val group = Group(
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getString(3).split(","),
                    cursor.getString(4)
                )
                groupList.add(group)
            } while (cursor.moveToNext())
        }
        return AlertMzg("SUCCESS", "Groups found", groupList)
    }

    fun getAllLoans(): AlertMzg<List<Loan>> {
        val loanList = ArrayList<Loan>()
        val db = this.readableDatabase
        val cursor = db.rawQuery("SELECT * FROM $TABLE_LOAN", null)
        if (cursor.moveToFirst()) {
            do {
                val loan = Loan(
                    cursor.getDouble(1),
                    cursor.getDouble(2),
                    cursor.getString(3),
                    cursor.getString(4),
                    cursor.getString(5),
                    cursor.getInt(6),
                    cursor.getString(7),
                    cursor.getString(8),
                    cursor.getString(9),
                    cursor.getString(10)
                )
                loanList.add(loan)
            } while (cursor.moveToNext())
        }
        return AlertMzg("SUCCESS", "Loans found", loanList)
    }

    fun getAllSubscriptions(): AlertMzg<List<Subscription>> {
        val subscriptionList = ArrayList<Subscription>()
        val db = this.readableDatabase
        val cursor = db.rawQuery("SELECT * FROM $TABLE_SUBSCRIPTION", null)
        if (cursor.moveToFirst()) {
            do {
                val subscription = Subscription(
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getString(3),
                    cursor.getInt(4),
                    cursor.getString(5),
                    cursor.getDouble(6)
                )
                subscriptionList.add(subscription)
            } while (cursor.moveToNext())
        }
        return AlertMzg("SUCCESS", "Subscriptions found", subscriptionList)
    }


}