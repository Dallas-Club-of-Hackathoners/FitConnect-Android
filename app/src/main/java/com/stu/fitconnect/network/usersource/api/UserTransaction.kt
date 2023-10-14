package com.stu.fitconnect.network.usersource.api

data class UserTransaction(
    val uId: String,
    val email: String,
    val uniqueUsername: String?,
    val phone: String?,
    val age: String?,
    val gender: String?
)