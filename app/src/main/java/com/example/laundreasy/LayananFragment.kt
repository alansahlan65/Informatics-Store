package com.example.laundreasy

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.laundreasy.data.produk.ProdukCallback
import com.example.laundreasy.data.produk.ProdukResponse
import com.example.laundreasy.databinding.FragmentLayananBinding
import com.example.laundreasy.ui.produk.ProdukAdapter
import com.example.laundreasy.ui.produk.ProdukViewModel

class LayananFragment : Fragment(R.layout.fragment_layanan) {

    private val produkViewModel : ProdukViewModel by activityViewModels()
    private lateinit var binding : FragmentLayananBinding
    private val produkAdapter = ProdukAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentLayananBinding.bind(view)

        produkViewModel.getProduk(object : ProdukCallback {
            override fun onResponse(response: ProdukResponse) {
                produkAdapter.updateListData(response.produk)
                binding.rvLayanan.layoutManager = LinearLayoutManager(activity)
                binding.rvLayanan.adapter = produkAdapter
                binding.loading.visibility = View.GONE
                binding.rvLayanan.visibility = View.VISIBLE
            }
        })
    }
}