package com.example.yourturn.network

import com.example.yourturn.data.UserRegistrModel
import com.example.yourturn.data.UserResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.HeaderMap
import retrofit2.http.POST

interface AuthService {

    @GET("auth/login")
    suspend fun login(@HeaderMap headers: Map<String, String>): Response<UserResponse>
   /* @POST("auth/register")
    suspend fun register(@Body user: UserRegistrModel): Unit*/

}