package com.example.laundreasy

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import com.example.laundreasy.data.user.User
import com.example.laundreasy.databinding.FragmentProfilBinding
import com.example.laundreasy.ui.user.UserViewModel

class ProfilFragment : Fragment(R.layout.fragment_profil) {

    private val userViewModel : UserViewModel by activityViewModels()
    private lateinit var binding : FragmentProfilBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentProfilBinding.bind(view)

        userViewModel.userData.observe(viewLifecycleOwner, Observer<User>{
            if(it == null){
                startActivity(Intent(activity, AuthActivity::class.java))
                requireActivity().finish()
            }else{
                binding.tvNama.text = userViewModel.userData.value!!.nama
                binding.tvEmail.text = userViewModel.userData.value!!.email
                binding.tvNo.text = userViewModel.userData.value!!.no
                binding.tvAlamat.text = userViewModel.userData.value!!.alamat
                binding.textView.text = "Profile"
            }
        })

        binding.btnEdit.setOnClickListener{
            val intent = Intent(activity, EditProfilActivity::class.java)
            intent.putExtra(EditProfilActivity.EXTRA_NAMA, binding.tvNama.text.toString())
            intent.putExtra(EditProfilActivity.EXTRA_EMAIL, binding.tvEmail.text.toString())
            intent.putExtra(EditProfilActivity.EXTRA_NO, binding.tvNo.text.toString())
            intent.putExtra(EditProfilActivity.EXTRA_ALAMAT, binding.tvAlamat.text.toString())
            startActivity(intent)
        }

        binding.btnLogout.setOnClickListener{
            userViewModel.logout()
            startActivity(Intent(activity, AuthActivity::class.java))
            requireActivity().finish()
        }
    }
}