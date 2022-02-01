package com.example.libertybank.fragments

import android.content.ContentValues
import android.os.Bundle
import android.util.Log
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
import com.example.libertybank.classes.User
import com.example.libertybank.databinding.FragmentHomeBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class HomeFragment: Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewPager : ViewPager2
    private lateinit var adapter : ViewPagerAdapter

    private lateinit var recyclerView: RecyclerView
    private lateinit var recyclerViewTransactionsAdapter: RecyclerViewTransactions

    private val db = Firebase.database.reference

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

        val mAuth = FirebaseAuth.getInstance()
        db.child("user").child(mAuth.currentUser?.uid.toString()).addValueEventListener(object :
            ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                // Get Post object and use the values to update the UI
                val userInfo = dataSnapshot.getValue(User::class.java)

                binding.textViewWelcome.text = "მოგესალმებით ${userInfo?.name}"
                // ...
            }

            override fun onCancelled(databaseError: DatabaseError) {
                // Getting Post failed, log a message
                Log.w(ContentValues.TAG, "loadPost:onCancelled", databaseError.toException())
            }
        })



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