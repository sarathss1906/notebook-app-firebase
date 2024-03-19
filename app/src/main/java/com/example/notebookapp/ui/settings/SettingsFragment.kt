package com.example.notebookapp.ui.settings

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.notebookapp.R
import com.example.notebookapp.databinding.FragmentSettingsBinding
import com.google.firebase.auth.FirebaseAuth

class SettingsFragment : Fragment() {

    private lateinit var binding:FragmentSettingsBinding
    private val firebaseAuth = FirebaseAuth.getInstance()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSettingsBinding.inflate(inflater,container,false)
        setListeners()
        return binding.root
    }

    private fun setListeners() {
        binding.btnLogout.setOnClickListener {
            binding.btnLogout.setOnClickListener {
                signOutAndNavigateToLoginScreen()
            }
        }
    }

    private fun signOutAndNavigateToLoginScreen() {
        firebaseAuth.signOut()
        findNavController().navigate(R.id.action_settingsFragment_to_loginFragment)

    }
}