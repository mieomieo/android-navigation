package com.example.navigation.common

import java.math.BigInteger
import java.security.MessageDigest

fun hashSHA256(input: String): String {
    val md = MessageDigest.getInstance("SHA-256")
    return BigInteger(1, md.digest(input.toByteArray())).toString(16).padStart(64, '0')
}