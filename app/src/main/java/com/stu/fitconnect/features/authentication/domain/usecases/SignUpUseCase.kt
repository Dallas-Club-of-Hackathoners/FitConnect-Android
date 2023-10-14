package com.stu.fitconnect.features.authentication.domain.usecases

import com.stu.fitconnect.features.authentication.domain.User
import com.stu.fitconnect.features.authentication.repositories.AuthenticationRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SignUpUseCase @Inject constructor(
    private val authenticationRepository: AuthenticationRepository
) {
    operator suspend fun invoke(user: User, password: String, repeatPassword: String, rememberUser: Boolean) {
        return authenticationRepository.signUp(user, password, repeatPassword, rememberUser)
    }

}
