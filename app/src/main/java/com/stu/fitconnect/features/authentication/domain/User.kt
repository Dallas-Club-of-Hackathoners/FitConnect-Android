package com.stu.fitconnect.features.authentication.domain

data class User(
    val uId: String? = null,
    val email: String,
    val uniqueUsername: String? = null,
    val phone: String? = null,
    val age: String? = null,
    val gender: String? = null
)