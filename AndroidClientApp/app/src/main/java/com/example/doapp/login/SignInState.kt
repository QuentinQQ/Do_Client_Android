package com.example.doapp.login

data class SignInState (
    val isSignInSuccessful: Boolean = false,
    val signInError: String? = null
)