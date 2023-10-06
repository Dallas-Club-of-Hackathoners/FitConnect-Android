package com.stu.fitconnect.features.authentication.domain.usecases

import com.stu.fitconnect.features.authentication.repositories.AuthenticationRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SignInUseCase @Inject constructor(
    private val authenticationRepository: AuthenticationRepository
) {
    operator suspend fun invoke(email: String, password: String, rememberUser: Boolean) {
        return authenticationRepository.signIn(email, password, rememberUser)
    }
}