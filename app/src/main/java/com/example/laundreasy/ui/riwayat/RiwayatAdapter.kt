package com.example.laundreasy.ui.produk

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.laundreasy.R
import com.example.laundreasy.data.pesanan.Pesanan
import java.time.ZoneId

class RiwayatAdapter : RecyclerView.Adapter<RiwayatAdapter.RiwayatViewHolder>() {

    private val _listPesanan: MutableList<Pesanan> = mutableListOf()

    inner class RiwayatViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        var tvNama : TextView = itemView.findViewById(R.id.tvNama_riwayat)
        var tvJumlah : TextView = itemView.findViewById(R.id.tvJumlah_riwayat)
        var tvHarga : TextView = itemView.findViewById(R.id.tvHarga_riwayat)
        var tvTanggal: TextView = itemView.findViewById(R.id.tvTanggal_riwayat)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RiwayatViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.riwayat_list, parent, false)
        return RiwayatViewHolder(view)
    }

    override fun onBindViewHolder(holder: RiwayatViewHolder, position: Int) {
        holder.itemView.apply {
            holder.tvNama.text = "${_listPesanan[position].namaProduk}"
            holder.tvJumlah.text = "Jumlah : ${_listPesanan[position].jumlah}"
            holder.tvHarga.text = "Total Harga : ${_listPesanan[position].totalHarga}"
            val hari = _listPesanan[position].tanggal.toInstant().atZone(ZoneId.systemDefault()).toLocalDate().dayOfMonth
            val bulan = _listPesanan[position].tanggal.toInstant().atZone(ZoneId.systemDefault()).toLocalDate().monthValue
            val tahun = _listPesanan[position].tanggal.toInstant().atZone(ZoneId.systemDefault()).toLocalDate().year
            holder.tvTanggal.text = "Tanggal : $hari/$bulan/$tahun"
        }
    }

    override fun getItemCount(): Int {
        return _listPesanan.size
    }

    fun updateListData(newPesanan : MutableList<Pesanan>){
        _listPesanan.clear()
        _listPesanan.addAll(newPesanan)
    }
}