package com.example.navigation.common

object Validate {
    fun isValidEmail(email: String): Boolean {
        val emailPattern = Regex("^[A-Za-z](.*)([@]{1})(.{1,})(\\.)(.{1,})")
        return emailPattern.matches(email)
    }

    fun isValidPassword(password: String): Boolean {
        return password.length >= 8
    }
}