package com.app.jendelapmi.models

data class UpdateProfileModel(
    val id: Int,
    val nama_pendonor: String,
    val email: String,
    val password: String,
    val alamat: String,
    val ttl: String,
    val golongan_darah: String
)
