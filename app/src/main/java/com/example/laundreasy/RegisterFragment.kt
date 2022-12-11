package com.example.laundreasy

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import com.example.laundreasy.databinding.FragmentRegisterBinding
import com.example.laundreasy.ui.user.UserViewModel

class RegisterFragment : Fragment(R.layout.fragment_register) {

    private val userViewModel: UserViewModel by activityViewModels()
    private lateinit var binding: FragmentRegisterBinding

    companion object {
        private const val STATE_NAMA = "state_nama"
        private const val STATE_EMAIL = "state_email"
        private const val STATE_PASSWORD = "state_password"
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentRegisterBinding.bind(view)

        userViewModel.isRegistered.observe(viewLifecycleOwner, Observer<Boolean>{ isRegistered->
            binding.loading.visibility = View.GONE
            if(isRegistered){
                startActivity(Intent(activity, MainActivity::class.java))
                requireActivity().finish()
            }
        })

        binding.btnRegister.setOnClickListener {
            val nama = binding.etNama.text.toString().trim()
            val email = binding.etEmail.text.toString().trim()
            val password = binding.etPassword.text.toString().trim()
            val no = binding.etNo.text.toString().trim()
            val alamat = binding.etAlamat.text.toString().trim()

            if (!userViewModel.isNameValid(nama)) {
                binding.etEmail.error = "Nama harus diisi!"
                return@setOnClickListener
            }

            if (!userViewModel.isEmailValid(email)) {
                binding.etEmail.error = "Email tidak valid!"
                return@setOnClickListener
            }

            if (!userViewModel.isPasswordValid(password)) {
                binding.etPassword.error = "Password harus diisi!"
                return@setOnClickListener
            }

            if (!userViewModel.isPasswordLength(password)) {
                binding.etPassword.error = "Password harus lebih dari 6 karakter!"
                return@setOnClickListener
            }

            if (!userViewModel.isNoValid(password)) {
                binding.etNo.error = "Nomor harus diisi!"
                return@setOnClickListener
            }

            if (!userViewModel.isAlamatValid(password)) {
                binding.etAlamat.error = "Alamat harus diisi!"
                return@setOnClickListener
            }

            binding.loading.visibility = View.VISIBLE
            userViewModel.register(nama, email, password, no, alamat)
        }
    }


    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString(STATE_NAMA, binding.etNama.text.toString().trim())
        outState.putString(STATE_EMAIL, binding.etEmail.text.toString().trim())
        outState.putString(STATE_PASSWORD, binding.etPassword.text.toString().trim())
    }
}