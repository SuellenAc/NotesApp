package com.androiddevs.ktornoteapp.data.remote.api

import com.androiddevs.ktornoteapp.data.remote.Authenticated
import com.androiddevs.ktornoteapp.data.remote.request.AccountRequest
import com.androiddevs.ktornoteapp.data.remote.request.AddOwnerRequest
import com.androiddevs.ktornoteapp.data.remote.request.DeleteNoteRequest
import com.androiddevs.ktornoteapp.data.remote.request.NoteRequest
import com.androiddevs.ktornoteapp.data.remote.response.NoteResponse
import com.androiddevs.ktornoteapp.data.remote.response.SimpleResponse
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface NoteApi {
    @Authenticated
    @POST("/addNote")
    suspend fun addNote(
        @Body note: NoteRequest,
    ): Response<ResponseBody>

    @Authenticated
    @POST("/deleteNote")
    suspend fun deleteNote(
        @Body deleteNoteRequest: DeleteNoteRequest,
    ): Response<ResponseBody>

    @Authenticated
    @POST("/addOwnerToNote")
    suspend fun addOwnerToNote(
        @Body addOwnerRequest: AddOwnerRequest,
    ): Response<SimpleResponse>

    @Authenticated
    @GET
    suspend fun getNotes(): Response<List<NoteResponse>>
}