package com.example.laundreasy

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import com.example.laundreasy.data.produk.ProdukCallback
import com.example.laundreasy.data.produk.ProdukResponse
import com.example.laundreasy.databinding.FragmentPesananBinding
import com.example.laundreasy.ui.produk.ProdukViewModel
import com.example.laundreasy.ui.pesanan.PesananViewModel
import java.text.SimpleDateFormat
import java.util.*


class PesananFragment : Fragment(R.layout.fragment_pesanan) {

    private lateinit var binding: FragmentPesananBinding
    private val pesananViewModel : PesananViewModel by activityViewModels()
    private val produkViewModel : ProdukViewModel by activityViewModels()
    private var spinnerPos = 0
    private val idProduk = mutableListOf<String>()
    private val namaProduk = mutableListOf<String>()
    val bulan = Calendar.getInstance().get(Calendar.MONTH) + 1
    val tahun = Calendar.getInstance().get(Calendar.YEAR)
    val hari = Calendar.getInstance().get(Calendar.DAY_OF_MONTH)
    val tanggal = SimpleDateFormat("yyyy-MM-dd").parse("$tahun-$bulan-$hari")

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentPesananBinding.bind(view)

        produkViewModel.getProduk(object : ProdukCallback {
            override fun onResponse(response: ProdukResponse) {
                for (id in response.idProduk){
                    idProduk.add(id)
                }
                for (produk in response.produk){
                    namaProduk.add(produk.nama)
                }
                val adapter = ArrayAdapter<String>(requireActivity(), android.R.layout.simple_spinner_dropdown_item, namaProduk)
                binding.spinner.adapter = adapter
            }
        })

        binding.spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                spinnerPos = position
            }
            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("Not yet implemented")
            }
        }

        binding.btnPesan.setOnClickListener {
            pesananViewModel.tambah(idProduk.get(spinnerPos), namaProduk.get(spinnerPos), binding.etJumlah.text.toString().trim().toInt(), tanggal)
            Toast.makeText(activity, "Pesanan berhasil ditambahkan.", Toast.LENGTH_SHORT).show()
        }
    }
}