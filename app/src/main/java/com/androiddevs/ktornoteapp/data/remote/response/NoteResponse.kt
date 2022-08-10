package com.androiddevs.ktornoteapp.data.remote.response

data class NoteResponse(
    val id: String,
    val title: String,
    val content: String,
    val date: Long,
    val owners: List<String>,
    val color: String,
)
