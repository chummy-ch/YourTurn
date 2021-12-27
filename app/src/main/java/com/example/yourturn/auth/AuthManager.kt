package com.example.yourturn.auth

import com.example.yourturn.data.User
import com.example.yourturn.data.UserResponse
import com.example.yourturn.network.RetrofitClient
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class AuthManager(private val retrofit: RetrofitClient) {

    private val _user: MutableStateFlow<UserResponse?> = MutableStateFlow(null)
    val user: StateFlow<UserResponse?> = _user.asStateFlow()

    private val service = retrofit.authService

    suspend fun login(login: String, psw: String) {
        val response = service.login(
            mapOf(
                "email" to login,
                "password" to psw
            )
        )
        if (response.code() == 200) {
            val user = response.body() ?: return
            _user.value = user
        }
    }

    fun logout() {
        _user.value = null
    }

    suspend fun getUsers(): List<User>? {
        val response = service.getUsers()

        if (response.code() == 200) {
            return response.body()
        } else {
            return null
        }
    }

    suspend fun removeUser(): Boolean {
        return false
    }
}