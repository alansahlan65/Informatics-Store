package com.example.laundreasy.admin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import com.example.laundreasy.databinding.ActivityKelolaLayananBinding
import com.example.laundreasy.ui.produk.ProdukViewModel

class KelolaLayananActivity : AppCompatActivity() {

    private val produkViewModel : ProdukViewModel by viewModels()
    private lateinit var binding : ActivityKelolaLayananBinding

    companion object{
        const val EXTRA_ID = "extra_id"
        const val EXTRA_NAMA = "extra_nama"
        const val EXTRA_DURASI = "extra_durasi"
        const val EXTRA_HARGA = "extra_harga"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityKelolaLayananBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.etNama.setText(intent.getStringExtra(KelolaLayananActivity.EXTRA_NAMA))
        binding.etDurasi.setText(intent.getStringExtra(KelolaLayananActivity.EXTRA_DURASI))
        binding.etHarga.setText(intent.getStringExtra(KelolaLayananActivity.EXTRA_HARGA))

        binding.btnSave.setOnClickListener {
            produkViewModel.tambah(
                intent.getStringExtra(EXTRA_ID),
                binding.etNama.text.toString(),
//                binding.etDurasi.text.toString().toInt(),
                binding.etHarga.text.toString().toDouble())
            finish()
            Toast.makeText(this, "Layanan Berhasil Diedit", Toast.LENGTH_SHORT).show()
        }
    }
}