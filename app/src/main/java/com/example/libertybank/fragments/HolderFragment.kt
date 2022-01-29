package com.example.libertybank.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.libertybank.R
import com.example.libertybank.databinding.FragmentHolderBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class HolderFragment: Fragment() {


    private var _binding: FragmentHolderBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHolderBinding.inflate(inflater, container, false)
        val view = binding.root

        val navHostFragment =
            childFragmentManager.findFragmentById(R.id.nav_host_fragment_fragment) as NavHostFragment
        val navController = navHostFragment.navController

        val navView: BottomNavigationView = (binding.bottomNavigationMenu)

        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.homeFragment,
                R.id.transactionsFragment,
                R.id.settingsFragment
            )
        )

        setupActionBarWithNavController(requireActivity() as AppCompatActivity, navController, appBarConfiguration)
        navView.setupWithNavController(navController)



        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

