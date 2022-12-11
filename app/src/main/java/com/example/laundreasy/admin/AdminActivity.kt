package com.example.laundreasy.admin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.laundreasy.R
import com.example.laundreasy.databinding.ActivityAdminBinding

class AdminActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAdminBinding
    private val kelolaLayananFragment = KelolaLayananFragment()
    private val kelolaPesananFragment = KelolaPesananFragment()
    private var title : String = "Kelola Pesanan"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAdminBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setActionBarTitle(title)

//        navigateToFragment(kelolaLayananFragment)

        binding.navBottom.setOnItemSelectedListener {
            setMode(it.itemId)
            true
        }
    }

    fun setMode(selectedMode: Int) {
        when (selectedMode){
            R.id.layanan_admin -> {
                navigateToFragment(kelolaLayananFragment)
                title ="Kelola Layanan"
            }
            R.id.pesanan_admin -> {
                navigateToFragment(kelolaPesananFragment)
                title ="Kelola Pesanan"
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