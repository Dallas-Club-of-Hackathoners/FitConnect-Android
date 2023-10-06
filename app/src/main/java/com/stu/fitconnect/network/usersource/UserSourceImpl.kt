package com.stu.fitconnect.network.usersource

import com.stu.fitconnect.features.authentication.domain.User

class UserSourceImpl: UsersSource {
    override suspend fun createUser( user: User) {
        TODO("Not yet implemented")
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