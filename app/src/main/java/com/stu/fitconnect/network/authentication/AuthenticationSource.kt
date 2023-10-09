package com.stu.fitconnect.network.authentication

interface AuthenticationSource {

    suspend fun signIn(email: String, password: String)

    suspend fun signUp(email: String, password: String)

    fun logOut()

    fun isSignedId(): Boolean

    fun getCurrentUId(): String

    suspend fun deleteCurrentUser()
}