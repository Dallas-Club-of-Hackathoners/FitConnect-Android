package com.stu.fitconnect.features.authentication.domain.usecases

import com.stu.fitconnect.features.authentication.repositories.AuthenticationRepository

class SignInUseCase(
    private val authenticationRepository: AuthenticationRepository
) {
    operator suspend fun invoke(email: String, password: String, rememberUser: Boolean) {
        return authenticationRepository.signIn(email, password, rememberUser)
    }
}