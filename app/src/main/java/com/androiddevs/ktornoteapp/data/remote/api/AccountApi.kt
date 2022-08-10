package com.androiddevs.ktornoteapp.data.remote.api

import com.androiddevs.ktornoteapp.data.remote.request.AccountRequest
import com.androiddevs.ktornoteapp.data.remote.response.SimpleResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface AccountApi {
    @POST("/register")
    suspend fun register(
        @Body account: AccountRequest,
    ): Response<SimpleResponse>

    @POST("/login")
    suspend fun login(
        @Body account: AccountRequest,
    ): Response<SimpleResponse>
}