package com.example.doapp.login

fun hasLettersAndDigits(password: String): Boolean {
    val hasUpperCase = password.any { it.isUpperCase() }
    val hasLowerCase = password.any { it.isLowerCase() }
    val hasDigits = password.any { it.isDigit() }
    return hasUpperCase && hasLowerCase && hasDigits
}

fun containsNoSpaces(password: String): Boolean {
    return !password.contains(" ")
}

