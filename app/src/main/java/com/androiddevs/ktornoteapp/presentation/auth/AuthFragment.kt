package com.androiddevs.ktornoteapp.presentation.auth

import android.annotation.SuppressLint
import android.content.pm.ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.androiddevs.ktornoteapp.R
import com.androiddevs.ktornoteapp.presentation.FragmentExtensions.showSnackBar
import com.androiddevs.ktornoteapp.utils.Status
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_auth.*

@AndroidEntryPoint
class AuthFragment : Fragment(R.layout.fragment_auth) {

    private val viewModel: AuthViewModel by  viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        subscribeToObservers()

        btnRegister.setOnClickListener {
            val email = etRegisterEmail.text.toString()
            val password = etRegisterPassword.text.toString()
            val confirmedPassword = etRegisterPasswordConfirm.text.toString()
            viewModel.register(
                email = email,
                password = password,
                repeatedPassword = confirmedPassword
            )
        }

        btnLogin.setOnClickListener {
            navigateToNotesScreen()
        }
    }

    private fun subscribeToObservers() {
        viewModel.registerStatus.observe(viewLifecycleOwner) { result ->
            result?.let {
                when (result.status) {
                    Status.Loading -> {
                        registerProgressBar.visibility = View.VISIBLE
                    }
                    Status.Success -> {
                        registerProgressBar.visibility = View.GONE
                        showSnackBar(result.data ?: "Successfully registered an account")
                    }
                    Status.Error -> {
                        registerProgressBar.visibility = View.GONE
                        showSnackBar(result.message ?: "Unknown error occurred")
                    }
                }
            }
        }
    }

    private fun navigateToNotesScreen() {
        findNavController().navigate(
            AuthFragmentDirections.actionAuthFragmentToNotesFragment()
        )
    }
}