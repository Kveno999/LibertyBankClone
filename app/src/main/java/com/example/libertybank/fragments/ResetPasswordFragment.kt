package com.example.libertybank.fragments

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.libertybank.R
import com.example.libertybank.databinding.FragmentResetPasswordBinding
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth

class ResetPasswordFragment: Fragment() {

    private var _binding: FragmentResetPasswordBinding? = null

    private val binding get() = _binding!!

    private lateinit var mProgressDialog: Dialog

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentResetPasswordBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        registerListeners()
        setupActionBar()
    }

    private fun registerListeners () {
        val buttonBackFromReset = binding.buttonBackResetPassword
        buttonBackFromReset.setOnClickListener {
            findNavController().navigate(R.id.action_resetPasswordFragment_to_loginFragment)
        }


    }
    private fun setupActionBar() {

        val submit = binding.buttonSendToEmail
        val email = binding.editTextEmailResetPassword

        submit.setOnClickListener {
            val email: String = email.text.toString().trim { it <= ' ' }
            if (email.isEmpty()){
                showErrorSnackBar(resources.getString(R.string.error_email), true)
            }else {
                showProgressDialog("Please Wait!")
                FirebaseAuth.getInstance().sendPasswordResetEmail(email)
                    .addOnCompleteListener { task ->
                        mProgressDialog.dismiss()
                        if (task.isSuccessful) {
                            showErrorSnackBar("კოდი წარმატებით გაიგზავნა!", false)

                        }else {
                            showErrorSnackBar(task.exception!!.message.toString(), true)
                        }

                    }
            }

        }

    }
    fun showProgressDialog(text: String) {

        mProgressDialog = Dialog(requireContext())

        mProgressDialog.setContentView(R.layout.dialog_progress)
        mProgressDialog.setCancelable(false)
        mProgressDialog.window?.setBackgroundDrawableResource(android.R.color.transparent)
        mProgressDialog.setCanceledOnTouchOutside(false)
        mProgressDialog.show()



    }


    fun showErrorSnackBar(message: String, errorMessage: Boolean) {
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