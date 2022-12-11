package com.example.laundreasy.data.produk

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase

class ProdukRepository {

    var _listProduk: MutableLiveData<MutableList<Produk>> = MutableLiveData(mutableListOf())
    var _listIdProduk: MutableLiveData<MutableList<String>> = MutableLiveData(mutableListOf())
    var _spinnerAdapter: MutableLiveData<MutableList<String>> = MutableLiveData(mutableListOf())
    private val db = Firebase.firestore.collection("produk")

    init {
        updateData()
    }

    fun tambah(id: String?, nama: String, harga: Double){
        if(id == null){
            db.add(Produk(nama, harga))
        }else{
            db.document(id).update("nama", nama)
            db.document(id).update("harga", harga)
        }
    }

    fun get(callback: ProdukCallback){
        db.get().addOnSuccessListener {
            val response = ProdukResponse()
            for (document in it) {
                response.idProduk.add(document.id)
                response.produk.add(document.toObject<Produk>())
                }
            callback.onResponse(response)
            }
            .addOnFailureListener { exception ->
                Log.i("TEST", "Error getting documents: ", exception)
            }
    }

    fun delete(id: String){
        db.document(id).delete().addOnSuccessListener {
            Log.w("TEST", "Success deleting document")
        }.addOnFailureListener {
            Log.w("TEST", "Error deleting document", it)
        }
    }

    fun updateData() {
        db.addSnapshotListener { snapshot, e ->
            if (e != null) {
                Log.w("TEST", "Listen failed.", e)
                return@addSnapshotListener
            }
            if (snapshot != null) {
                val listProduk: MutableList<Produk> = mutableListOf()
                val listIdProduk: MutableList<String> = mutableListOf()
                val spinnerAdapter : MutableList<String> = mutableListOf()
                for (doc in snapshot){
                    listProduk.add(doc.toObject<Produk>())
                    listIdProduk.add(doc.id)
                    spinnerAdapter.add(doc.data.get("nama").toString())
                }
                _listProduk.value = listProduk
                _listIdProduk.value = listIdProduk
                _spinnerAdapter.value = spinnerAdapter
            } else {
                Log.d("TEST", "Current data: null")
            }
        }
    }
}