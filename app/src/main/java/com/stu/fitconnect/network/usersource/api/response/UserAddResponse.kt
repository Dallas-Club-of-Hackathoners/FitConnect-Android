package com.stu.fitconnect.network.usersource.api.response

import com.google.gson.annotations.SerializedName

data class UsersAddResponse(
    @SerializedName("status") val status: Boolean
)