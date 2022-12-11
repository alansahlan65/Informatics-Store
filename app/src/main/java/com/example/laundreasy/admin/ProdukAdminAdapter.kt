package com.example.laundreasy.admin

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.laundreasy.R
import com.example.laundreasy.data.produk.Produk

class ProdukAdminAdapter : RecyclerView.Adapter<ProdukAdminAdapter.ProdukViewHolder>() {

    private val _listProduk: MutableList<Produk> = mutableListOf()
    private val _listIdProduk: MutableList<String> = mutableListOf()
    private lateinit var onItemClickCallback: OnItemClickCallback

    interface OnItemClickCallback {
        fun onItemClicked(id: String, data: Produk)
        fun onItemClicked2(id: String, data: Produk)
    }

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    inner class ProdukViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        var tvNama : TextView = itemView.findViewById(R.id.tvNama)
//        var tvDesc : TextView = itemView.findViewById(R.id.tvDesc)
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
//            holder.tvDesc.text = "Durasi : ${_listLayanan[position].durasi} Hari"
            holder.tvHarga.text = " Harga : Rp.${_listProduk[position].harga}"
            holder.itemView.setOnClickListener {onItemClickCallback.onItemClicked(_listIdProduk[holder.adapterPosition], _listProduk[holder.adapterPosition])}
            holder.btnDelete.setOnClickListener { onItemClickCallback.onItemClicked2(_listIdProduk[holder.adapterPosition], _listProduk[holder.adapterPosition])}
        }
    }

    override fun getItemCount(): Int {
        return _listProduk.size
    }

    fun updateListData(newProduk : MutableList<Produk>, newidProduk : MutableList<String>){
        _listProduk.clear()
        _listProduk.addAll(newProduk)

        _listIdProduk.clear()
        _listIdProduk.addAll(newidProduk)
    }
}