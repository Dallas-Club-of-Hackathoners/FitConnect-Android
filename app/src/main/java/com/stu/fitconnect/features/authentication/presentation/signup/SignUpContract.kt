package com.stu.fitconnect.features.authentication.presentation.signup

import com.stu.fitconnect.base.UnidirectionalViewModel
import com.stu.fitconnect.features.authentication.presentation.login.LoginContract

interface SignUpContract : UnidirectionalViewModel<LoginContract.State, LoginContract.Event> {

    data class State(
        val isLoading: Boolean = false,
        val email: String = "",
        val password: String = "",
        val rememberUser: Boolean = false,
    )

    sealed class Event {
        object OnSignIn : Event()
        data class OnChangeRememberUser(val rememberUser: Boolean) : Event()
        data class OnEmailChanged(val email: String) : Event()
        data class OnPasswordChanged(val password: String) : Event()
    }

}