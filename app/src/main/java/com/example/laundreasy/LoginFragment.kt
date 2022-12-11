package com.example.laundreasy

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
//import com.example.laundreasy.admin.AdminActivity
import com.example.laundreasy.databinding.FragmentLoginBinding
import com.example.laundreasy.ui.user.UserViewModel

class LoginFragment : Fragment(R.layout.fragment_login) {

    private val userViewModel : UserViewModel by activityViewModels()
    private lateinit var binding : FragmentLoginBinding

    companion object {
        private const val STATE_EMAIL = "state_email"
        private const val STATE_PASSWORD = "state_password"
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentLoginBinding.bind(view)

        if (savedInstanceState != null) {
            val result = savedInstanceState.getString(STATE_EMAIL)
            binding.etEmail.setText(result)
        }

        binding.btnLogin.setOnClickListener {
            val email = binding.etEmail.text.toString().trim()
            val password = binding.etPassword.text.toString().trim()

            if(!userViewModel.isEmailValid(email)){
                binding.etEmail.error = "Email yang anda masukan salah!"
                return@setOnClickListener
            }

            if(!userViewModel.isPasswordValid(password)){
                binding.etPassword.error = "Password yang anda masukan salah!"
                return@setOnClickListener
            }

            binding.loading.visibility = View.VISIBLE
            userViewModel.login(email, password)
        }

        binding.btnRegister.setOnClickListener {
            parentFragmentManager.beginTransaction().apply {
                replace(R.id.fragment_container, RegisterFragment())
                addToBackStack(null)
                commit()
            }
        }

//        binding.btnAdmin.setOnClickListener {
//            startActivity(Intent(activity, AdminActivity::class.java))
//            requireActivity().finish()
//        }

    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString(STATE_EMAIL, binding.etEmail.text.toString().trim())
        outState.putString(STATE_PASSWORD, binding.etPassword.text.toString().trim())
    }

}