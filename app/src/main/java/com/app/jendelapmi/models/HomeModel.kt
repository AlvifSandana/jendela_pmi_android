package com.app.jendelapmi.models

data class HomeModel(val status: String, val message: String, val data: ArrayList<Data>){
    data class Data(val id: Int,
                    val nama_kegiatan: String,
                    val tanggal_kegiatan: String,
                    val lokasi_kegiatan: String,
                    val foto: String)
}
