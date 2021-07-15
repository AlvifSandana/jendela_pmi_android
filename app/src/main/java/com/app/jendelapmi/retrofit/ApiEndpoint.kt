package com.app.jendelapmi.retrofit

import com.app.jendelapmi.models.HomeModel
import com.app.jendelapmi.models.LoginModel
import com.app.jendelapmi.models.LoginResponseModel
import retrofit2.Call
import retrofit2.http.*

interface ApiEndpoint {
    @GET("kegiatan")
    fun getKegiatan(): Call<HomeModel>

    @FormUrlEncoded
    @POST("login/pendonor")
    fun doLogin(
        @Field("email") email: String,
        @Field("password") password: String
    ): Call<LoginResponseModel>
}