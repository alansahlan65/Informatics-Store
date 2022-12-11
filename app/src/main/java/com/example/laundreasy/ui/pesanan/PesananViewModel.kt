package com.example.laundreasy.ui.pesanan

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.laundreasy.data.pesanan.Pesanan
import com.example.laundreasy.data.pesanan.PesananCallback
import com.example.laundreasy.data.pesanan.PesananRepository
import java.util.*

class PesananViewModel : ViewModel() {

    private var _listPesanan : MutableLiveData<MutableList<Pesanan>> = MutableLiveData(arrayListOf())
    val listPesanan : LiveData<MutableList<Pesanan>>
        get() = _listPesanan

    private var _listAllPesanan : MutableLiveData<MutableList<Pesanan>> = MutableLiveData(arrayListOf())
    val listAllPesanan : LiveData<MutableList<Pesanan>>
        get() = _listAllPesanan

    private val repository = PesananRepository()

    init{
        _listPesanan = repository._listPesanan
        _listAllPesanan = repository._listAllPesanan
    }

    fun tambah(idLayanan: String, namaLayanan: String, berat: Int, tanggal: Date) {
        repository.tambah(idLayanan, namaLayanan, berat, tanggal)
    }

    fun tambahAdmin(idLayanan: String, namaLayanan: String, namaCostumer: String, noCostumer:String, berat: Int, tanggal: Date) {
        repository.tambahAdmin(idLayanan, namaLayanan, namaCostumer, noCostumer, berat, tanggal)
    }

    fun get() {
    }

    fun getAll(callback: PesananCallback){
        repository.getAll(callback)
    }

    fun edit(idPesanan: String, status: String) {
        repository.edit(idPesanan, status)
    }

    fun delete() {

    }

}
