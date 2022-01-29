package com.example.libertybank.fragments

import android.app.Dialog
import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.libertybank.R
import com.example.libertybank.classes.User
import com.example.libertybank.databinding.FragmentLoginBinding
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.database.ktx.getValue
import com.google.firebase.ktx.Firebase

class LoginFragment: Fragment() {
    private var _binding: FragmentLoginBinding? = null

    private val binding get() = _binding!!

    private lateinit var mProgressDialog: Dialog

    private val db = Firebase.database.reference

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        val view = binding.root





        return view
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        registerListeners()

    }

    private fun registerListeners() {

        val buttonGoToRegister = binding.textViewGoToRegister
        val buttonLogin = binding.buttonLogin
        val textViewForgotPassword = binding.textViewForgotPassword
        val mAuth = FirebaseAuth.getInstance()





        buttonLogin.setOnClickListener {
            val email = binding.editTextEmailLogin.text.toString()
            val password = binding.editTextPasswordLogin.text.toString()
            if (email.isEmpty() || password.isEmpty()) {
                showErrorSnackBar("შეიყვანეთ მეილი სწორად!", true)
                return@setOnClickListener
            }


            showProgressDialog("Please wait!")


            FirebaseAuth.getInstance()
            FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->


                    db.child("user").child(mAuth.currentUser?.uid.toString()).addValueEventListener(object : ValueEventListener{
                        override fun onDataChange(dataSnapshot: DataSnapshot) {
                            // Get Post object and use the values to update the UI
                            val userInfo = dataSnapshot.getValue(User::class.java)


                            if (task.isSuccessful && userInfo?.profileCompleted != 0) {
                                findNavController().navigate(R.id.action_loginFragment_to_holderFragment)
                                mProgressDialog.dismiss()
                            }else{
                                showErrorSnackBar("ავტორიზაცია ვერ მოხერხდა!", true)
                                findNavController().navigate(R.id.action_loginFragment_to_profileDetialsFragment)
                                mProgressDialog.dismiss()
                            }
                            // ...
                        }

                        override fun onCancelled(databaseError: DatabaseError) {
                            // Getting Post failed, log a message
                            Log.w(TAG, "loadPost:onCancelled", databaseError.toException())
                        }
                    })



                }

        }
        buttonGoToRegister.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_registerFragment)
        }
        textViewForgotPassword.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_resetPasswordFragment)
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

    fun showProgressDialog(text: String) {

        mProgressDialog = Dialog(requireContext())

        mProgressDialog.setContentView(R.layout.dialog_progress)
        mProgressDialog.setCancelable(false)
        mProgressDialog.window?.setBackgroundDrawableResource(android.R.color.transparent)
        mProgressDialog.setCanceledOnTouchOutside(false)
        mProgressDialog.show()



    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}