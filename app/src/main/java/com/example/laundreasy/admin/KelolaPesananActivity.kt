package com.example.laundreasy.admin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.Toast
import androidx.activity.viewModels
import com.example.laundreasy.databinding.ActivityKelolaPesananBinding
import com.example.laundreasy.ui.pesanan.PesananViewModel

class KelolaPesananActivity : AppCompatActivity() {

    private val pesananViewModel : PesananViewModel by viewModels()
    private lateinit var binding : ActivityKelolaPesananBinding
    private var status = ""

    companion object{
        const val EXTRA_ID_PESANAN = "extra_id_pesanan"
        const val EXTRA_ID_COSTUMER = "extra_id_costumer"
        const val EXTRA_NAMA_COSTUMER = "extra_nama_costumer"
        const val EXTRA_NO_COSTUMER = "extra_no_costumer"
        const val EXTRA_ID_LAYANAN = "extra_id_layanan"
        const val EXTRA_NAMA_LAYANAN = "extra_nama_layanan"
        const val EXTRA_BERAT = "extra_berat"
        const val EXTRA_HARGA = "extra_harga"
        const val EXTRA_STATUS = "extra_status"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityKelolaPesananBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.etNamaCostumer.setText(intent.getStringExtra(EXTRA_NAMA_COSTUMER))
        binding.etNoCostumer.setText(intent.getStringExtra(EXTRA_NO_COSTUMER))
        binding.etNamaLayanan.setText(intent.getStringExtra(EXTRA_NAMA_LAYANAN))
        binding.etJumlah.setText(intent.getStringExtra(EXTRA_BERAT).toString())
        binding.etHarga.setText(intent.getStringExtra(EXTRA_HARGA).toString())

        when(intent.getStringExtra(EXTRA_STATUS)){
            "MENUNGGU KONFIRMASI" -> binding.spnStatus.setSelection(0)
            "DIJEMPUT" -> binding.spnStatus.setSelection(1)
            "DICUCI" -> binding.spnStatus.setSelection(2)
            "DISETRIKA" -> binding.spnStatus.setSelection(3)
            "SELESAI" -> binding.spnStatus.setSelection(4)
            "DIANTAR" -> binding.spnStatus.setSelection(5)
        }


        binding.spnStatus.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                status = parent?.getItemAtPosition(position).toString()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("Not yet implemented")
            }
        }

        binding.btnSave.setOnClickListener {
            pesananViewModel.edit(intent.getStringExtra(EXTRA_ID_PESANAN)!!, status)
            Toast.makeText(this, "Pesanan Berhasil Diedit", Toast.LENGTH_SHORT).show()
            finish()
        }
    }
}