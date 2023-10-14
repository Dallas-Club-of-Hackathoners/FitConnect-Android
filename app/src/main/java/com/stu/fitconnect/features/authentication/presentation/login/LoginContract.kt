package com.stu.fitconnect.features.authentication.presentation.login

import com.stu.fitconnect.base.UnidirectionalViewModel
import com.stu.fitconnect.features.authentication.domain.AuthField
import com.stu.fitconnect.features.authentication.domain.SignInData

interface LoginContract: UnidirectionalViewModel<LoginContract.State, LoginContract.Event> {

    data class State(
        val isLoading: Boolean = false,
        val signInData: SignInData = SignInData(),
        val rememberUser: Boolean = false,
    )

    sealed class Event {

        data class OnLogin(val navigateToMainScreen: () -> Unit) : Event()
///        object OnNavigateToMain : Event()
////        object OnForgotPassword : Event()
        data class OnChangeRememberUser(val rememberUser: Boolean) : Event()
        data class OnSignInDataChanged(val value: String, val type: AuthField) : Event()
    }

}
