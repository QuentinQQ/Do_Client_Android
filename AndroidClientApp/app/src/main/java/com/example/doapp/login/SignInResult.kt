package com.example.doapp.login

class SignInResult(
    val data: UserData?,
    val errorMessage: String?
)

class UserData(
    val userId: String,
    val username: String?,
    val profilePictureUrl: String?
)