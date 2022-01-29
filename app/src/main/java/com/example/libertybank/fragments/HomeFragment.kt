package com.example.libertybank.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.example.libertybank.adapters.ViewPagerAdapter
import com.example.libertybank.classes.RecyclerViewTransactions
import com.example.libertybank.databinding.FragmentHomeBinding
import com.google.android.material.tabs.TabLayout

class HomeFragment: Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewPager : ViewPager2
    private lateinit var adapter : ViewPagerAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val view = binding.root



        init()

        return view
    }
    private fun init (){

        viewPager = binding.viewPager
        adapter = ViewPagerAdapter(this)
        viewPager.adapter = adapter


        binding.transactionsRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.transactionsRecyclerView.adapter = RecyclerViewTransactions()


    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}