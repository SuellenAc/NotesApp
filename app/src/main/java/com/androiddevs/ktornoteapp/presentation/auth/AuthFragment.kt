package com.androiddevs.ktornoteapp.presentation.auth

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.androiddevs.ktornoteapp.R
import kotlinx.android.synthetic.main.fragment_auth.*

class AuthFragment : Fragment(R.layout.fragment_auth) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btnLogin.setOnClickListener {
            navigateToNotesScreen()
        }
    }

    private fun navigateToNotesScreen() {
        findNavController().navigate(
            AuthFragmentDirections.actionAuthFragmentToNotesFragment()
        )
    }
}