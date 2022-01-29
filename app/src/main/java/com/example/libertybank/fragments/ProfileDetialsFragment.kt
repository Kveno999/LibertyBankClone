package com.example.libertybank.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.libertybank.R
import com.example.libertybank.classes.User
import com.example.libertybank.databinding.FragmentProfileDetialsBinding
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class ProfileDetialsFragment: Fragment() {

    private var _binding: FragmentProfileDetialsBinding? = null

    private val binding get() = _binding!!

    private val db = Firebase.database.reference

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentProfileDetialsBinding.inflate(inflater, container, false)
        val view = binding.root


        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        registerListeners()

    }

    private fun registerListeners (){
        val editTextName = binding.editTextName.text
        val editTextSurname = binding.editTextSurname.text
        val editTextCardNumber = binding.editTextCardNumber.text
        val buttonSave = binding.buttonSave
        val mAuth = FirebaseAuth.getInstance()
        val user = User()


        buttonSave.setOnClickListener {
            db.child("user").child(mAuth.currentUser?.uid.toString()).child("name").setValue(editTextName.toString())
            db.child("user").child(mAuth.currentUser?.uid.toString()).child("surname").setValue(editTextSurname.toString())
            db.child("user").child(mAuth.currentUser?.uid.toString()).child("card").setValue(editTextCardNumber.toString())
            db.child("user").child(mAuth.currentUser?.uid.toString()).child("profileCompleted").setValue(1)
            showErrorSnackBar("თქვენი მონაცემები წარმატებით შეინახა!", false)
            findNavController().navigate(R.id.action_profileDetialsFragment_to_loginFragment)
        }

    }
    private fun showErrorSnackBar(message: String, errorMessage: Boolean) {
        val snackBar =
            Snackbar.make(requireView(), message, Snackbar.LENGTH_LONG)
        val snackBarView = snackBar.view
        if (errorMessage) {
            snackBarView.setBackgroundColor(
                ContextCompat.getColor(
                    requireContext(),
                    R.color.colorSnackBarError
                )
            )
        } else {
            snackBarView.setBackgroundColor(
                ContextCompat.getColor(
                    requireContext(),
                    R.color.colorSnackBarSuccess
                )
            )
        }
        snackBar.show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}