package com.app.jendelapmi.models

data class StokDarahModel(
    val status: String,
    val message: String,
    val data: ArrayList<Data>
    ){
    data class Data(
        val produk: String,
        val stok : Stok
    ){
      data class Stok(
          val A: Int,
          val B: Int,
          val AB: Int,
          val O: Int,
          val Total: Int
      )
    }
}
