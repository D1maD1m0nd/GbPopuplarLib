package com.example.gbpopularlibs.utils

class ValidateAuth {
    private enum class RegexValidateConstants(val value: String) {
        CORRECT_PASSWORD("^[\\S+]{5,40}"), CORRECT_LOGIN("^\\D[\\S+$]{3,40}")
    }

    fun checkPassword(password: String): Boolean {
        return Regex(RegexValidateConstants.CORRECT_PASSWORD.value).matches(password)
    }

    fun checkLogin(login: String): Boolean {
        return Regex(RegexValidateConstants.CORRECT_LOGIN.value).matches(login)
    }
}