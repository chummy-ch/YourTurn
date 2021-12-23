package com.example.yourturn.data

 data class UserResponse(
     val email: String,
     val password: String,
     val role: String
)

data class UserRegistrModel(
    val email: String,
    val password: String,
    val role: String
)