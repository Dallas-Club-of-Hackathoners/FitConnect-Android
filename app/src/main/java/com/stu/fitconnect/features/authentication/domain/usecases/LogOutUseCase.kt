package com.stu.fitconnect.features.authentication.domain.usecases

import com.stu.fitconnect.features.authentication.repositories.AuthenticationRepository

class LogOutUseCase(
    private val authenticationRepository: AuthenticationRepository
) {
    suspend operator fun invoke() {
        return authenticationRepository.logOut()
    }
}