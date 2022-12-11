package com.example.laundreasy.admin

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.laundreasy.R

import com.example.laundreasy.data.pesanan.Pesanan
import com.example.laundreasy.data.pesanan.PesananCallback
import com.example.laundreasy.data.pesanan.PesananResponse
import com.example.laundreasy.databinding.FragmentKelolaPesananBinding
import com.example.laundreasy.ui.pesanan.PesananViewModel

class KelolaPesananFragment : Fragment(R.layout.fragment_kelola_pesanan) {

    private val pesananViewModel : PesananViewModel by activityViewModels()
    private val pesananAdminAdapter = PesananAdminAdapter()
    private lateinit var binding : FragmentKelolaPesananBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentKelolaPesananBinding.bind(view)

        pesananAdminAdapter.setOnItemClickCallback(object: PesananAdminAdapter.OnItemClickCallback{
            override fun onItemClicked(id: String, data: Pesanan) {
                val intent = Intent(activity, KelolaPesananActivity::class.java)
                intent.putExtra(KelolaPesananActivity.EXTRA_ID_PESANAN, id)
                intent.putExtra(KelolaPesananActivity.EXTRA_ID_COSTUMER, data.idCustomer)
                intent.putExtra(KelolaPesananActivity.EXTRA_NAMA_COSTUMER, data.namaCostumer)
                intent.putExtra(KelolaPesananActivity.EXTRA_NO_COSTUMER, data.noCostumer)
                intent.putExtra(KelolaPesananActivity.EXTRA_ID_LAYANAN, data.idProduk)
                intent.putExtra(KelolaPesananActivity.EXTRA_NAMA_LAYANAN, data.namaProduk)
                intent.putExtra(KelolaPesananActivity.EXTRA_BERAT, data.jumlah.toString())
                intent.putExtra(KelolaPesananActivity.EXTRA_HARGA, data.totalHarga.toString())
//                intent.putExtra(KelolaPesananActivity.EXTRA_STATUS, data.status)
                startActivity(intent)

            }
        })
        binding.btnTambah.setOnClickListener {
            startActivity(Intent(activity, PesanAdminActivity::class.java))
        }

        pesananViewModel.listAllPesanan.observe(viewLifecycleOwner, Observer<MutableList<Pesanan>>{
            getData()
        })
    }

    fun getData(){
        pesananViewModel.getAll(object : PesananCallback {
            override fun onResponse(response: PesananResponse) {
                pesananAdminAdapter.updateListData(response.pesanan, response.idPesanan)
                binding.rvPesanan.layoutManager = LinearLayoutManager(activity)
                binding.rvPesanan.adapter = pesananAdminAdapter
                binding.loading.visibility = View.GONE
                binding.rvPesanan.visibility = View.VISIBLE
            }
        })
    }
}