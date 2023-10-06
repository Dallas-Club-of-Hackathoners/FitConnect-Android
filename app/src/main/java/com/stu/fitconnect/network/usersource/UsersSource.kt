package com.stu.fitconnect.network.usersource

import com.stu.fitconnect.features.authentication.domain.User

interface UsersSource {

    suspend fun createUser(user: User)

    suspend fun getUserData(uId: String): User

    suspend fun checkUniqueName(uniqueName: String): Boolean

    suspend fun deleteUser(uId: String)

    suspend fun getUserDataByUniqueName(uniqueName: String): User
}