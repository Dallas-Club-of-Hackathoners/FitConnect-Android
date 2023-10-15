package com.stu.fitconnect.features.authentication.presentation.signup

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.stu.fitconnect.features.authentication.domain.AuthField
import com.stu.fitconnect.features.authentication.domain.User
import com.stu.fitconnect.features.authentication.domain.usecases.SignUpUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val signUpUseCase: SignUpUseCase
): ViewModel(), SignUpContract {

    private val mutableScreenState = MutableStateFlow(SignUpContract.State())
    override val state: StateFlow<SignUpContract.State> = mutableScreenState.asStateFlow()

    override fun event(event: SignUpContract.Event) = when (event) {
        is SignUpContract.Event.OnSignIn -> onSignUp(event.navigateToMainScreen)
//        LoginContract.Event.OnForgotPassword -> onForgotPassword()
        is SignUpContract.Event.OnSignInDataChanged -> updateState {
            when(event.type) {
                AuthField.Email -> it.copy(signUpData = it.signUpData.copy(email = event.value))
                AuthField.Password -> it.copy(signUpData = it.signUpData.copy(password = event.value))
                AuthField.RepeatPassword -> it.copy(signUpData = it.signUpData.copy(repeatPassword = event.value))
            }
        }
        is SignUpContract.Event.OnChangeRememberUser -> updateState { it.copy(rememberUser = event.rememberUser) }
    }

    private fun onSignUp(navigateToMainScreen: () -> Unit) {
        mutableScreenState.update { it.copy(isLoading = true) }
        viewModelScope.launch {
            try {
                signUpUseCase.invoke(
                    User(email = mutableScreenState.value.signUpData.email),
                    mutableScreenState.value.signUpData.password,
                    mutableScreenState.value.signUpData.repeatPassword,
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

    private fun updateState(updateAction: (SignUpContract.State) -> SignUpContract.State) {
        mutableScreenState.update { updateAction(it) }
    }

}
