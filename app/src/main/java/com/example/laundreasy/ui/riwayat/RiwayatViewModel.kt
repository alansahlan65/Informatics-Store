package com.example.laundreasy.ui.riwayat

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.laundreasy.data.pesanan.Pesanan
import com.example.laundreasy.data.pesanan.PesananCallback
import com.example.laundreasy.data.pesanan.PesananRepository

class RiwayatViewModel : ViewModel(){

    private var _listPesanan: MutableLiveData<MutableList<Pesanan>> = MutableLiveData(mutableListOf())
    val listPesanan: LiveData<MutableList<Pesanan>>
        get() = _listPesanan

    private val repository = PesananRepository()

    init{
        _listPesanan = repository._listPesanan
    }

    fun getRiwayat(callback: PesananCallback){
        repository.get(callback)
    }
}