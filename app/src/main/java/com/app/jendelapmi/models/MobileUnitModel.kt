package com.app.jendelapmi.models

data class MobileUnitModel(
    val status: String,
    val message: String,
    val data: ArrayList<Data>
    ) {
    data class Data(
        val id: Int,
        val tanggal_donor: String,
        val lokasi_donor: String,
        val waktu_mulai: String,
        val waktu_selesai: String,
        val deskripsi: String
    )
}
