package com.stu.fitconnect.features.authentication.presentation.login

import com.stu.fitconnect.base.UnidirectionalViewModel

interface LoginContract: UnidirectionalViewModel<LoginContract.State, LoginContract.Event> {

    data class State(
        val isLoading: Boolean = false,
        val email: String = "",
        val password: String = "",
        val rememberUser: Boolean = false,
    )

    sealed class Event {
        object OnLogin : Event()
////        object OnForgotPassword : Event()
        data class OnChangeRememberUser(val rememberUser: Boolean) : Event()
        data class OnEmailChanged(val email: String) : Event()
        data class OnPasswordChanged(val password: String) : Event()
    }

}
