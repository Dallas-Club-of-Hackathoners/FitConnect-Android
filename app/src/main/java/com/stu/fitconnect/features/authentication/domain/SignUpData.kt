package com.stu.fitconnect.features.authentication.domain

data class SignUpData (
    val email: String = "",
    val password: String = "",
    val repeatPassword: String = ""
)