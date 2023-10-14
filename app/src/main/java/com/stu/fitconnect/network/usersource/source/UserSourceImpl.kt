package com.stu.fitconnect.network.usersource.source

import com.stu.fitconnect.features.authentication.domain.User
import com.stu.fitconnect.network.usersource.api.UsersApiService
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserSourceImpl @Inject constructor(
    val usersApiService: UsersApiService
): UsersSource {
    override suspend fun createUser(user: User) {
        try {
            if(user.uId == null /*|| user.uniqueUsername == null*/) throw Exception()
            val result = usersApiService.createUser(userId = user.uId, name = user.email)
            val i = result
        } catch (e: Exception) {
            throw e
        }
    }

    override suspend fun getUserData(uId: String): User {
        TODO("Not yet implemented")
    }

    override suspend fun checkUniqueName(uniqueName: String): Boolean {
        TODO("Not yet implemented")
    }

    override suspend fun deleteUser(uId: String) {
        TODO("Not yet implemented")
    }

    override suspend fun getUserDataByUniqueName(uniqueName: String): User {
        TODO("Not yet implemented")
    }
}