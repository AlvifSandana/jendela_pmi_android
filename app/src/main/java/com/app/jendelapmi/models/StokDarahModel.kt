package com.app.jendelapmi.models

data class StokDarahModel(
    val status: String,
    val message: String,
    val data: ArrayList<Data>
    ){
    data class Data(
        val id: Int,
        val produk: String,
        val jenis_darah: String,
        val jumlah: Int,
    )
}
