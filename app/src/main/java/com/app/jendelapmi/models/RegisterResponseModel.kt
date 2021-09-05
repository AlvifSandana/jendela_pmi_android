package com.app.jendelapmi.models

data class RegisterResponseModel(
    val status: String,
    val message: String,
    val data: Data
) {
    data class Data(
        val nama: String,
        val email: String,
        val alamat: String,
        val ttl: String,
        val golongan_darah: String,
        val api_token: String
    )
}
