package com.stu.fitconnect.network.usersource.api

import com.stu.fitconnect.network.usersource.api.request.UsersAddRequest
import com.stu.fitconnect.network.usersource.api.response.UsersAddResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface UsersApiService {
    @POST("user/add")
    suspend fun createUser(
        @Body usersAddRequest: UsersAddRequest
    ): UsersAddResponse
}