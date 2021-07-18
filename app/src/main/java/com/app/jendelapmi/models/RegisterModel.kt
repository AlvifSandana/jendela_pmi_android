package com.app.jendelapmi.models

data class RegisterModel(
    val nama_pendonor: String,
    val email: String,
    val password : String,
    val ttl: String,
    val alamat: String,
    val golongan_darah: String
)
