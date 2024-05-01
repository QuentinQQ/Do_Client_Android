package com.example.doapp.login

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class SignInViewModel {
    private val _state = MutableStateFlow(SignInState())
    val state = _state.asStateFlow()


    fun onSignInResult(result: SignInResult) {
        _state.update { currentState ->
            currentState.copy(
                isSignInSuccessful = result.data != null,
                signInError = result.errorMessage
            )
        }
    }

    fun resetState(){
        _state.update{ SignInState()}
    }
}