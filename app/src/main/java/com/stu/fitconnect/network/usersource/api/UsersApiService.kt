package com.stu.fitconnect.network.usersource.api

import retrofit2.http.POST
import retrofit2.http.Query

interface UsersApiService {
    @POST("user/add")
    suspend fun createUser(
        @Query("nick_name") name: String,
        @Query("token") userId: String,
    ): Boolean
}