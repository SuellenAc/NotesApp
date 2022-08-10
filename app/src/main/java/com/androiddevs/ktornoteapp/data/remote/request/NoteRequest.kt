package com.androiddevs.ktornoteapp.data.remote.request

data class NoteRequest(
    val id: String,
    val title: String,
    val content: String,
    val date: Long,
    val owners: List<String>,
    val color: String,
)
