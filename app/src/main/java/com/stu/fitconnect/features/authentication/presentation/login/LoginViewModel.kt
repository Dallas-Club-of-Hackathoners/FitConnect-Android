package com.stu.fitconnect.features.authentication.presentation.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.stu.fitconnect.features.authentication.domain.usecases.SignInUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUseCase: SignInUseCase,

):ViewModel(), LoginContract {

    private val mutableScreenState = MutableStateFlow(LoginContract.State())
    override val state: StateFlow<LoginContract.State> = mutableScreenState.asStateFlow()


    override fun event(event: LoginContract.Event) = when (event) {
        LoginContract.Event.OnLogin -> onLogin()
//        LoginContract.Event.OnForgotPassword -> onForgotPassword()
        is LoginContract.Event.OnEmailChanged -> updateStateAndFetchData{it.copy(email = event.email)}
        is LoginContract.Event.OnPasswordChanged -> updateStateAndFetchData{it.copy(password = event.password)}
        is LoginContract.Event.OnChangeRememberUser -> updateStateAndFetchData { it.copy(rememberUser = event.rememberUser) }
    }

    private fun onLogin() {
        mutableScreenState.update { it.copy(isLoading = true) }
        try {
            viewModelScope.launch {
                loginUseCase.invoke(
                    mutableScreenState.value.email,
                    mutableScreenState.value.password,
                    mutableScreenState.value.rememberUser
                )
            }
        } catch (e: Exception) {
            // todo handle exception
            // add side effect?
        } finally {
            mutableScreenState.update { it.copy(isLoading = false) }
        }
    }


//    private fun onForgotPassword() {
//        TODO("Not yet implemented")
//    }

    private fun updateStateAndFetchData(updateAction: (LoginContract.State) -> LoginContract.State) {
        mutableScreenState.update { updateAction(it) }
    }

}
