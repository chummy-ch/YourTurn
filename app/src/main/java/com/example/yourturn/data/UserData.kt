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

data class Restriction(
    val name: String,
    val desc: String,
    val amount: Int
)

data class User(
    val _id: String,
    val email: String,
    val password: String,
    val role: String,
    val _v: Int
)