package com.stu.fitconnect.features.authentication.repositories

import com.stu.fitconnect.utils.AuthException
import com.stu.fitconnect.features.authentication.domain.User
import com.stu.fitconnect.network.authentication.AuthenticationSource
import com.stu.fitconnect.network.usersource.source.UsersSource
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthenticationRepositoryImpl @Inject constructor(
    private val authenticationSource: AuthenticationSource,
    private val usersSource: UsersSource,
): AuthenticationRepository {

    override suspend fun signIn(email: String, password: String, rememberUser: Boolean) {
        authenticationSource.signIn(email, password)
        val uId = authenticationSource.getCurrentUId()
            //val userData = usersSource.getUserData(uId ?: throw AuthException("Auth error. Cant get current User Id"))
        //TODO добавить user в локальную бд
    }

    override suspend fun signUp(user: User, password: String, repeatPassword: String, rememberUser: Boolean) {
        authenticationSource.signUp(user.email, password) // create account in firebase authentication

        try {
            val uId: String = authenticationSource.getCurrentUId()
            usersSource.createUser(user.copy(uId = uId))
        } catch (e: Exception) {
            authenticationSource.deleteCurrentUser()
            throw e
        }
    }

    override suspend fun deleteCurrentUser() {
        val uId: String = authenticationSource.getCurrentUId()
        authenticationSource.deleteCurrentUser()
        usersSource.deleteUser(uId)
     }


    override fun logOut() = authenticationSource.logOut()

    override fun isSignedIn(): Boolean = authenticationSource.isSignedId()

//    private fun validate(user: User, email: String, password: String, repeatPassword: String? = null) {
//        if(email.isBlank()) throw (EmptyFieldException(AuthField.Email))
//        if(password.isBlank()) throw (EmptyFieldException(AuthField.Password))
//        if(repeatPassword != null && repeatPassword.isBlank()) throw (EmptyFieldException(AuthField.RepeatPassword))
//    }


}