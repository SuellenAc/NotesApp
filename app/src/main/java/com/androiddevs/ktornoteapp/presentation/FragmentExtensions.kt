package com.androiddevs.ktornoteapp.presentation

import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*

object FragmentExtensions {

    fun Fragment.showSnackBar(text: String) {
        Snackbar.make(
            requireActivity().rootLayout,
            text,
            Snackbar.LENGTH_LONG
        ).show()
    }
}