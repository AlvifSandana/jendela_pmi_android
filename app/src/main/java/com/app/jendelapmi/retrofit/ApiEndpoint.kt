package com.app.jendelapmi.retrofit

import com.app.jendelapmi.models.*
import retrofit2.Call
import retrofit2.http.*

interface ApiEndpoint {
    // get kegiatan
    @GET("kegiatan")
    fun getKegiatan(): Call<HomeModel>

    // get stok darah
    @GET("stokdarah")
    fun getStokDarah(
        @Header("api_token") api_token: String,
        @Header("role") role: String
    ) : Call<StokDarahModel>

    // get jadwal mobile unit
    @GET("mobileunit")
    fun getMobileUnit(
        @Header("api_token") api_token: String,
        @Header("role") role: String
    ) : Call<MobileUnitModel>

    // post login
    @FormUrlEncoded
    @POST("login/pendonor")
    fun doLogin(
        @Field("email") email: String,
        @Field("password") password: String
    ): Call<LoginResponseModel>

    // post register
    @FormUrlEncoded
    @POST("reg/pendonor")
    fun doRegister(
        @Field("nama_pendonor") nama: String,
        @Field("email") email: String,
        @Field("password") password: String,
        @Field("alamat") alamat: String,
        @Field("ttl") ttl: String,
        @Field("golongan_darah") golongan_darah: String
    ): Call<RegisterResponseModel>
}