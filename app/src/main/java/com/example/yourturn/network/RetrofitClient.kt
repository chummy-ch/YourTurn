package com.example.yourturn.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitClient {
    companion object {
        private const val BASE_URL = "http://192.168.0.100:3000/api/"
    }

    val authService: AuthService
        get() = getClient().create(AuthService::class.java)

    private var retrofit: Retrofit? = null

    private fun getClient(): Retrofit {
        if (retrofit == null) {
            retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
        return retrofit!!
    }
}