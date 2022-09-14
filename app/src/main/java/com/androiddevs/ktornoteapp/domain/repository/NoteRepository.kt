package com.androiddevs.ktornoteapp.domain.repository

import android.content.Context
import com.androiddevs.ktornoteapp.data.local.NoteDao
import com.androiddevs.ktornoteapp.data.remote.api.AccountApi
import com.androiddevs.ktornoteapp.data.remote.request.AccountRequest
import com.androiddevs.ktornoteapp.utils.Resource
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class NoteRepository @Inject constructor(
    private val noteDao: NoteDao,
    private val accountApi: AccountApi,
    @ApplicationContext private val context: Context,
) {
    suspend fun register(email: String, password: String) = withContext(Dispatchers.IO) {
        try {
            val response = accountApi.register(AccountRequest(email, password))
            if (response.isSuccessful) {
                Resource.success(response.body()?.message.orEmpty())
            } else {
                Resource.error(response.message(), data = null)
            }
        } catch (exception: Exception) {
            Resource.error(
                msg = "Couldn't connect to the servers. Check your internet connection",
                data = null
            )
        }
    }
}