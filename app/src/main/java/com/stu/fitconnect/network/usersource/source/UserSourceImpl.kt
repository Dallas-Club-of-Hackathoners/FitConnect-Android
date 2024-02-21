package com.stu.fitconnect.network.usersource.source

import com.stu.fitconnect.features.authentication.domain.User
import com.stu.fitconnect.network.usersource.api.UsersApiService
import com.stu.fitconnect.network.usersource.api.request.UsersAddRequest
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserSourceImpl @Inject constructor(
    private val usersApiService: UsersApiService
): UsersSource {
    override suspend fun createUser(user: User) {
        try {
            if(user.uId == null /*|| user.uniqueUsername == null*/) throw Exception()
            val result = usersApiService.createUser(UsersAddRequest(name = user.email, userId = user.uId))
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