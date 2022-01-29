package com.example.libertybank.fragments

import android.content.ContentValues
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.libertybank.R
import com.example.libertybank.classes.User
import com.example.libertybank.databinding.FragmentCardFirstBinding
import com.example.libertybank.databinding.FragmentSettingsBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class CardFirstFragment: Fragment(R.layout.fragment_card_first) {

    private var _binding: FragmentCardFirstBinding? = null

    private val binding get() = _binding!!

    private val db = Firebase.database.reference

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCardFirstBinding.inflate(inflater, container, false)
        val view = binding.root


        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val mAuth = FirebaseAuth.getInstance()
        db.child("user").child(mAuth.currentUser?.uid.toString()).addValueEventListener(object :
            ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                // Get Post object and use the values to update the UI
                val userInfo = dataSnapshot.getValue(User::class.java)

                binding.tvName.text = userInfo?.name
                binding.tvCardId.text = userInfo?.card
                // ...
            }

            override fun onCancelled(databaseError: DatabaseError) {
                // Getting Post failed, log a message
                Log.w(ContentValues.TAG, "loadPost:onCancelled", databaseError.toException())
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}