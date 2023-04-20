package com.example.rx.issue.dto

data class SignRequest (
    val email: String,
    val password: String,
    val username: String,
)

data class SignInRequest(
    val email: String,
    val password: String,
)

data class SignInResponse(
    val email: String,
    val username: String,
    val token: String,
)