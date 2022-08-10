package com.androiddevs.ktornoteapp.data.remote.request

data class AddOwnerRequest(
    val owner: String,
    val noteId: String,
)
