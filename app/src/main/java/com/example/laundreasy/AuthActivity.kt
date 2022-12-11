package com.example.laundreasy

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.example.laundreasy.databinding.ActivityAuthBinding
import com.example.laundreasy.ui.user.UserViewModel
import com.google.firebase.auth.FirebaseUser

class AuthActivity : AppCompatActivity() {

    private val userViewModel : UserViewModel by viewModels()
    private lateinit var binding: ActivityAuthBinding
    private val loginFragment = LoginFragment()
    private val registerFragment = RegisterFragment()
    private var title : String = "Login"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAuthBinding.inflate(layoutInflater)
        setContentView(binding.root)

        userViewModel.user.observe(this, Observer<FirebaseUser> { user ->
            if (user != null) {
                startActivity(Intent(this, MainActivity::class.java))
                finish()
            }
        })
    }

    fun setMode(selectedMode: Int) {
        when (selectedMode){
            R.id.home -> {
                navigateToFragment(loginFragment)
                title ="Login"
            }
            R.id.produk -> {
                navigateToFragment(registerFragment)
                title ="Register"
            }
        }
        setActionBarTitle(title)
    }

    private fun navigateToFragment(fragment: Fragment){
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fragment_container, fragment)
            addToBackStack(null)
            commit()
        }
    }

    private fun setActionBarTitle(title: String) {
        supportActionBar?.title = title
    }
}