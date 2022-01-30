package com.example.libertybank.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.libertybank.classes.RecyclerViewTransactions
import com.example.libertybank.classes.Transactions
import com.example.libertybank.databinding.FragmentTransactionsBinding

class TransactionsFragment: Fragment() {

    private var _binding: FragmentTransactionsBinding? = null

    private val binding get() = _binding!!

    private lateinit var recyclerView: RecyclerView
    private lateinit var recyclerViewTransactionsAdapter: RecyclerViewTransactions

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentTransactionsBinding.inflate(inflater, container, false)
        val view = binding.root



        recyclerView = binding.transactionsRecyclerView2
        recyclerViewTransactionsAdapter = RecyclerViewTransactions()
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = recyclerViewTransactionsAdapter


        return view
    }



    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}