package com.example.laundreasy

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import com.example.laundreasy.databinding.ActivityEditProfilBinding
import com.example.laundreasy.ui.user.UserViewModel

class EditProfilActivity : AppCompatActivity() {

    private val userViewModel : UserViewModel by viewModels()
    private lateinit var binding : ActivityEditProfilBinding

    companion object{
        const val EXTRA_EMAIL = "extra_email"
        const val EXTRA_NAMA = "extra_nama"
        const val EXTRA_NO = "extra_no"
        const val EXTRA_ALAMAT = "extra_alamat"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditProfilBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.etEmail.setText(intent.getStringExtra(EXTRA_EMAIL))
        binding.etNama.setText(intent.getStringExtra(EXTRA_NAMA))
        binding.etNo.setText(intent.getStringExtra(EXTRA_NO))
        binding.etAlamat.setText(intent.getStringExtra(EXTRA_ALAMAT))

        binding.btnSave.setOnClickListener {
            userViewModel.editData(binding.etNama.text.toString().trim(), binding.etAlamat.text.toString().trim())
            finish()
            Toast.makeText(this, "Profil Berhasil Diedit", Toast.LENGTH_SHORT).show()
        }
    }
}