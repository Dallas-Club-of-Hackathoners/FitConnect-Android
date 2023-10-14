package com.stu.fitconnect.features.authentication.presentation.signup

import com.stu.fitconnect.base.UnidirectionalViewModel
import com.stu.fitconnect.features.authentication.domain.AuthField
import com.stu.fitconnect.features.authentication.domain.SignInData
import com.stu.fitconnect.features.authentication.domain.SignUpData
import com.stu.fitconnect.features.authentication.presentation.login.LoginContract

interface SignUpContract : UnidirectionalViewModel<SignUpContract.State, SignUpContract.Event> {

    data class State(
        val isLoading: Boolean = false,
        val signUpData: SignUpData = SignUpData(),
        val rememberUser: Boolean = false,
    )

    sealed class Event {
        data class OnSignIn(val navigateToMainScreen: () -> Unit) : Event()
        data class OnChangeRememberUser(val rememberUser: Boolean) : SignUpContract.Event()
        data class OnSignInDataChanged(val value: String, val type: AuthField) : SignUpContract.Event()
    }

}