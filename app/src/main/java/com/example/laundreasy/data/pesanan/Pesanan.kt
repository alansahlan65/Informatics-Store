package com.example.laundreasy.data.pesanan

import java.util.*

data class Pesanan(
    val idCustomer : String = "",
    val emailCostumer: String ="",
    val namaCostumer: String ="",
    val noCostumer: String ="",
    val idProduk: String = "",
    val namaProduk: String = "",
    val jumlah: Int = 0,
    val totalHarga: Double = 0.0,
    val tanggal: Date = Date()
//    val status: String = "Menunggu Konfirmasi")
)