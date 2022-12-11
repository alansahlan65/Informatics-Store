package com.example.laundreasy.ui.produk

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.laundreasy.data.produk.Produk
import com.example.laundreasy.data.produk.ProdukCallback
import com.example.laundreasy.data.produk.ProdukRepository

class ProdukViewModel : ViewModel() {

    private var _listProduk: MutableLiveData<MutableList<Produk>> = MutableLiveData(mutableListOf())
    val listProduk: LiveData<MutableList<Produk>>
        get() = _listProduk

    private var _spinnerAdapter: MutableLiveData<MutableList<String>> = MutableLiveData(mutableListOf())
    val spinnerAdapter: LiveData<MutableList<String>>
        get() = _spinnerAdapter

    private val repository = ProdukRepository()

    init {
        _listProduk = repository._listProduk
        _spinnerAdapter = repository._spinnerAdapter
    }

    fun tambah(id: String?, nama: String, harga: Double){
        repository.tambah(id, nama, harga)
    }

    fun getProduk(callback: ProdukCallback){
        repository.get(callback)
    }

    fun delete(id: String){
        repository.delete(id)
    }
}