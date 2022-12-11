package com.example.laundreasy.admin

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.laundreasy.R
import com.example.laundreasy.data.produk.Produk
import com.example.laundreasy.data.produk.ProdukCallback
import com.example.laundreasy.data.produk.ProdukResponse
import com.example.laundreasy.databinding.FragmentKelolaLayananBinding

import com.example.laundreasy.ui.produk.ProdukViewModel

class KelolaLayananFragment : Fragment(R.layout.fragment_kelola_layanan) {

    private val layananViewModel : ProdukViewModel by activityViewModels()
    private val produkAdminAdapter = ProdukAdminAdapter()
    private lateinit var binding : FragmentKelolaLayananBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentKelolaLayananBinding.bind(view)
//        getData()

        layananViewModel.listProduk.observe(viewLifecycleOwner, Observer<MutableList<Produk>>{
            getData()
        })

        produkAdminAdapter.setOnItemClickCallback(object: ProdukAdminAdapter.OnItemClickCallback{
            override fun onItemClicked(id: String, data: Produk) {
                val intent = Intent(activity, KelolaLayananActivity::class.java)
                intent.putExtra(KelolaLayananActivity.EXTRA_ID, id)
                intent.putExtra(KelolaLayananActivity.EXTRA_NAMA, data.nama)
//                intent.putExtra(KelolaLayananActivity.EXTRA_DURASI, data.durasi.toString())
                intent.putExtra(KelolaLayananActivity.EXTRA_HARGA, data.harga.toString())
                startActivity(intent)
            }

            override fun onItemClicked2(id: String, data: Produk) {
                layananViewModel.delete(id)
            }
        })

        binding.btnTambah.setOnClickListener {
            startActivity(Intent(activity, KelolaLayananActivity::class.java))
        }
    }

    fun getData(){
        layananViewModel.getProduk(object : ProdukCallback {
            override fun onResponse(response: ProdukResponse) {
                produkAdminAdapter.updateListData(response.produk, response.idProduk)
                binding.rvLayanan.layoutManager = LinearLayoutManager(activity)
                binding.rvLayanan.adapter = produkAdminAdapter
                binding.loading.visibility = View.GONE
                binding.rvLayanan.visibility = View.VISIBLE
            }
        })
    }


}