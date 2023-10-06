package com.stu.fitconnect.features.authentication.repositories

import com.stu.fitconnect.AuthWeakPasswordException
import com.stu.fitconnect.features.authentication.domain.User

interface AuthenticationRepository {

    suspend fun signIn(email: String, password: String, rememberUser: Boolean)

    suspend fun signUp(user: User, password: String, repeatPassword: String, rememberUser: Boolean)

    suspend fun deleteCurrentUser()

    fun logOut()

    fun isSignedIn(): Boolean

}