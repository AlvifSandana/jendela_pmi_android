package com.app.jendelapmi.models

data class UpdateProfileResponseModel(
    val status: String,
    val message: String,
    val data: List<Data>
) {
    data class Data(
        val nama_pendonor: String,
        val email: String,
        val password: String,
        val alamat: String,
        val ttl: String,
        val golongan_darah: String,
        val status: String,
        val api_token: String
    )
}
