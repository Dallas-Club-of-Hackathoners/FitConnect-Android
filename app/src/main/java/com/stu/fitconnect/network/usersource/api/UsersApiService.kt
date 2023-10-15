package com.stu.fitconnect.network.usersource.api

import com.google.gson.annotations.SerializedName
import retrofit2.http.Body
import retrofit2.http.POST

interface UsersApiService {
    @POST("user/add")
    suspend fun createUser(
        @Body usersAddRequest: UsersAddRequest
    ): UsersAddResponse
}

data class UsersAddResponse(
    @SerializedName("status") val status: Boolean
)

data class UsersAddRequest(
    @SerializedName("nick_name") val name: String,
    @SerializedName("token") val userId: String
)