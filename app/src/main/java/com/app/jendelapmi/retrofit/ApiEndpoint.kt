package com.app.jendelapmi.retrofit

import com.app.jendelapmi.models.HomeModel
import retrofit2.Call
import retrofit2.http.GET

interface ApiEndpoint {
    @GET("kegiatan")
    fun getKegiatan(): Call<HomeModel>
}