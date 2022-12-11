package com.example.laundreasy.ui.produk

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.laundreasy.R
import com.example.laundreasy.data.produk.Produk

class ProdukAdapter : RecyclerView.Adapter<ProdukAdapter.ProdukViewHolder>() {

    private val _listProduk: MutableList<Produk> = mutableListOf()

    inner class ProdukViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        var tvNama : TextView = itemView.findViewById(R.id.tvNama)
        var tvHarga : TextView = itemView.findViewById(R.id.tvHarga)
        var btnDelete: ImageButton = itemView.findViewById(R.id.btnDelete)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProdukViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.produk_list, parent, false)
        return ProdukViewHolder(view)
    }

    override fun onBindViewHolder(holder: ProdukViewHolder, position: Int) {
        holder.itemView.apply {
            holder.tvNama.text = _listProduk[position].nama
            holder.tvHarga.text = "Harga : Rp.${_listProduk[position].harga}"
            holder.btnDelete.visibility = View.GONE
        }
        holder.itemView.setOnClickListener() {
            Toast.makeText(holder.itemView.context, "Kamu memilih produk " + _listProduk[position].nama, Toast.LENGTH_SHORT).show()
        }

    }

    override fun getItemCount(): Int {
        return _listProduk.size
    }

    fun updateListData(newProduk : MutableList<Produk>){
        _listProduk.clear()
        _listProduk.addAll(newProduk)
    }
}