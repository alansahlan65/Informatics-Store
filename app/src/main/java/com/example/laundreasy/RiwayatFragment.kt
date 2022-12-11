package com.example.laundreasy

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.laundreasy.data.pesanan.Pesanan
import com.example.laundreasy.data.pesanan.PesananCallback
import com.example.laundreasy.data.pesanan.PesananResponse
import com.example.laundreasy.databinding.FragmentRiwayatBinding
import com.example.laundreasy.ui.produk.RiwayatAdapter
import com.example.laundreasy.ui.riwayat.RiwayatViewModel

class RiwayatFragment : Fragment(R.layout.fragment_riwayat) {

    private val riwayatViewModel : RiwayatViewModel by activityViewModels()
    private lateinit var binding : FragmentRiwayatBinding
    private val riwayatAdapter = RiwayatAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentRiwayatBinding.bind(view)

        riwayatViewModel.listPesanan.observe(viewLifecycleOwner, Observer<MutableList<Pesanan>>{
            getData()
        })
    }

    fun getData(){
        riwayatViewModel.getRiwayat(object : PesananCallback {
            override fun onResponse(response: PesananResponse) {
                riwayatAdapter.updateListData(response.pesanan)
                binding.rvRiwayat.layoutManager = LinearLayoutManager(activity)
                binding.rvRiwayat.adapter = riwayatAdapter
                binding.loading.visibility = View.GONE
                binding.rvRiwayat.visibility = View.VISIBLE
            }
        })
    }

}