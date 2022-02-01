package com.example.libertybank.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.libertybank.R
import com.example.libertybank.databinding.FragmentSettingsBinding
import com.google.firebase.auth.FirebaseAuth

class SettingsFragment: Fragment() {

    private var _binding: FragmentSettingsBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSettingsBinding.inflate(inflater, container, false)
        val view = binding.root




        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        registerListeners()
    }

    private fun registerListeners () {
        val buttonLogout = binding.viewLogout
        val buttonProfile = binding.viewProfile
        buttonLogout.setOnClickListener {
            FirebaseAuth.getInstance().signOut()
            findNavController().navigate(R.id.action_settingsFragment_to_mainActivity)
        }
        buttonProfile.setOnClickListener {
            findNavController().navigate(R.id.action_settingsFragment_to_profileFragment)
        }
    }



    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}