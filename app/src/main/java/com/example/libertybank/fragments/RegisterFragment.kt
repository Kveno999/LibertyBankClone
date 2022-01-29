package com.example.libertybank.fragments

import android.app.Dialog
import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.libertybank.R
import com.example.libertybank.databinding.FragmentRegisterBinding
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class RegisterFragment: Fragment() {

    private var _binding: FragmentRegisterBinding? = null

    private val binding get() = _binding!!

    private lateinit var mProgressDialog: Dialog

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentRegisterBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        registerListeners()

    }

    private fun registerListeners() {

        val backToLogin = binding.buttonBack
        val buttonRegister = binding.buttonRegister

        buttonRegister.setOnClickListener {
            registerUser()
        }

        backToLogin.setOnClickListener {
            findNavController().navigate(R.id.action_registerFragment_to_loginFragment)
        }

    }
    private fun validateRegisterDetails(): Boolean {
        return when {
            TextUtils.isEmpty(binding.editTextEmailRegister.text.toString().trim { it <= ' '}) -> {
                showErrorSnackBar(resources.getString(R.string.error_first_name), true)
                false
            }
            TextUtils.isEmpty(binding.editTextPasswordRegister.text.toString().trim { it <= ' '}) -> {
                showErrorSnackBar(resources.getString(R.string.error_last_name), true)
                false
            }
            TextUtils.isEmpty(binding.editTextPasswordRepeatRegister.text.toString().trim { it <= ' '}) -> {
                showErrorSnackBar(resources.getString(R.string.error_email), true)
                false
            }
            else -> {
                true
            }


        }
    }
    private fun registerUser (){

        if (validateRegisterDetails()){

            showProgressDialog("Please Wait!")

            val email: String = binding.editTextEmailRegister.text.toString().trim { it <= ' ' }
            val password: String = binding.editTextPasswordRegister.text.toString().trim { it <= ' ' }

            FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(
                    OnCompleteListener<AuthResult> { task ->


                        if (task.isSuccessful) {


                            mProgressDialog.dismiss()

                            findNavController().navigate(R.id.action_registerFragment_to_profileDetialsFragment)

                        } else {
                            mProgressDialog.dismiss()
                            showErrorSnackBar(task.exception!!.message.toString(), true)

                        }


                    }
                )



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