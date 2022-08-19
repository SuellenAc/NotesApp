package com.androiddevs.ktornoteapp.presentation.notes

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.androiddevs.ktornoteapp.R
import kotlinx.android.synthetic.main.fragment_notes.*

class NotesFragment : Fragment(R.layout.fragment_notes) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fabAddNote.setOnClickListener {
            navigateToAddEditFragment()
        }
    }

    private fun navigateToAddEditFragment() {
        findNavController().navigate(
            NotesFragmentDirections.actionNotesFragmentToAddEditNoteFragment("")
        )
    }
}