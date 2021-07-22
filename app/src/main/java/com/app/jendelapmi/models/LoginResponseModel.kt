package com.app.jendelapmi.models

data class LoginResponseModel(
    val status: String,
    val message: String,
    val data: List<Data>
){
    data class Data(
        val id: Int,
        val nama_pendonor: String,
        val email: String,
        val password: String,
        val alamat: String,
        val telepon: String,
        val ttl: String,
        val golongan_darah: String,
        val status: String,
        val api_token: String,
        val roles_id: Int,
        val user_id: Int
    )
}
