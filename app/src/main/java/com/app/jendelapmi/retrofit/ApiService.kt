package com.app.jendelapmi.retrofit

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiService {
    val BASE_URL = "http://192.168.0.101:8000/api/"
    val endpoint: ApiEndpoint
        get() {
            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            return retrofit.create(ApiEndpoint::class.java)
        }
}