package com.banking.bankingsystem.dto

data class LoginRequest(
    val username: String,
    val password: String
)

data class LoginResponse(
    val token: String,
    val username: String,
    val email: String,
    val role: String
) 