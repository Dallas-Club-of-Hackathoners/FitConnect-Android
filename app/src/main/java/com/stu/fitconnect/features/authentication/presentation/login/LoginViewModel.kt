package com.stu.fitconnect.features.authentication.presentation.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.stu.fitconnect.features.authentication.domain.AuthField
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
        is LoginContract.Event.OnLogin -> onLogin(event.navigateToMainScreen)
//        LoginContract.Event.OnForgotPassword -> onForgotPassword()
        is LoginContract.Event.OnSignInDataChanged -> updateState {
            when(event.type) {
                AuthField.Email -> it.copy(signInData = it.signInData.copy(email = event.value))
                AuthField.Password -> it.copy(signInData = it.signInData.copy(password = event.value))
                else -> throw Exception()
            }
        }
        is LoginContract.Event.OnChangeRememberUser -> updateState { it.copy(rememberUser = event.rememberUser) }
    }

    private fun onLogin(navigateToMainScreen: () -> Unit) {
        mutableScreenState.update { it.copy(isLoading = true) }
        viewModelScope.launch() {
            try {
                loginUseCase.invoke(
                    mutableScreenState.value.signInData.email,
                    mutableScreenState.value.signInData.password,
                    mutableScreenState.value.rememberUser
                )
                navigateToMainScreen()
            } catch (e: Exception) {
                // todo handle exception
                // add side effect?
            } finally {
                mutableScreenState.update { it.copy(isLoading = false) }
            }
        }


    }


//    private fun onForgotPassword() {
//        TODO("Not yet implemented")
//    }

    private fun updateState(updateAction: (LoginContract.State) -> LoginContract.State) {
        mutableScreenState.update { updateAction(it) }
    }

}
