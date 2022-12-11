package com.example.laundreasy.admin

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.laundreasy.R
import com.example.laundreasy.data.pesanan.Pesanan
import java.time.ZoneId

class PesananAdminAdapter : RecyclerView.Adapter<PesananAdminAdapter.PesananViewHolder>() {

    private val _listPesanan: MutableList<Pesanan> = mutableListOf()
    private val _listIdPesanan: MutableList<String> = mutableListOf()
    private lateinit var onItemClickCallback: OnItemClickCallback

    interface OnItemClickCallback {
        fun onItemClicked(id: String, data: Pesanan)
    }

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    inner class PesananViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        var tvNamaCostumer : TextView = itemView.findViewById(R.id.tvNamaCostumer_pesanan)
        var tvNoCostumer : TextView = itemView.findViewById(R.id.tvNoCostumer_pesanan)
        var tvStatus : TextView = itemView.findViewById(R.id.tvStatus_pesanan)
        var tvNamaLayanan : TextView = itemView.findViewById(R.id.tvNamaLayanan_pesanan)
        var tvBerat : TextView = itemView.findViewById(R.id.tvBerat_pesanan)
        var tvHarga : TextView = itemView.findViewById(R.id.tvHarga_pesanan)
        var tvTanggal : TextView = itemView.findViewById(R.id.tvTanggal_pesanan)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PesananViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.pesanan_list, parent, false)
        return PesananViewHolder(view)
    }

    override fun onBindViewHolder(holder: PesananViewHolder, position: Int) {
        holder.itemView.apply {
            holder.tvNamaCostumer.text = _listPesanan[position].namaCostumer
            holder.tvNoCostumer.text = _listPesanan[position].noCostumer
//            holder.tvStatus.text = _listPesanan[position].status
            holder.tvNamaLayanan.text =  "Paket ${_listPesanan[position].namaProduk}"
            holder.tvBerat.text = "Berat : ${_listPesanan[position].jumlah}"
            holder.tvHarga.text = "Total Harga : Rp.${_listPesanan[position].totalHarga}"
            val hari = _listPesanan[position].tanggal.toInstant().atZone(ZoneId.systemDefault()).toLocalDate().dayOfMonth
            val bulan = _listPesanan[position].tanggal.toInstant().atZone(ZoneId.systemDefault()).toLocalDate().monthValue
            val tahun = _listPesanan[position].tanggal.toInstant().atZone(ZoneId.systemDefault()).toLocalDate().year
            holder.tvTanggal.text = "Tanggal: $hari/$bulan/$tahun"

            holder.itemView.setOnClickListener {onItemClickCallback.onItemClicked(_listIdPesanan[holder.adapterPosition], _listPesanan[holder.adapterPosition])}
        }
    }

    override fun getItemCount(): Int {
        return _listPesanan.size
    }

    fun updateListData(newPesanan : MutableList<Pesanan>, newIdPesanan: MutableList<String>){
        _listPesanan.clear()
        _listPesanan.addAll(newPesanan)

        _listIdPesanan.clear()
        _listIdPesanan.addAll(newIdPesanan)
    }
}