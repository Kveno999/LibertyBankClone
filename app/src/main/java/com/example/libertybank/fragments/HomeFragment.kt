package com.example.libertybank.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.example.libertybank.adapters.ViewPagerAdapter
import com.example.libertybank.classes.RecyclerViewTransactions
import com.example.libertybank.classes.Transactions
import com.example.libertybank.databinding.FragmentHomeBinding

class HomeFragment: Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewPager : ViewPager2
    private lateinit var adapter : ViewPagerAdapter

    private lateinit var recyclerView: RecyclerView
    private lateinit var recyclerViewTransactionsAdapter: RecyclerViewTransactions

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




        recyclerView = binding.transactionsRecyclerView
        recyclerViewTransactionsAdapter = RecyclerViewTransactions()
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = recyclerViewTransactionsAdapter


    }




    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}