package com.stu.fitconnect.network.usersource.api.request

import com.google.gson.annotations.SerializedName

data class UsersAddRequest(
    @SerializedName("nick_name") val name: String,
    @SerializedName("token") val userId: String
)