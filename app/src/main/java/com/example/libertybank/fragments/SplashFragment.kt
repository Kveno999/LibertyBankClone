package com.example.libertybank.fragments

import android.os.Bundle
import android.os.Handler
import android.view.*
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.libertybank.R
import com.example.libertybank.databinding.FragmentSplashBinding

class SplashFragment: Fragment() {
    private var _binding: FragmentSplashBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSplashBinding.inflate(inflater, container, false)
        val view = binding.root


        @Suppress("DEPRECATION")
        Handler().postDelayed(
            {
                findNavController().navigate(R.id.action_splashFragment_to_loginFragment)

            },2000
        )




        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}