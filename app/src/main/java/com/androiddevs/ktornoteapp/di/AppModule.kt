package com.androiddevs.ktornoteapp.di

import android.content.Context
import android.content.SharedPreferences
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey
import com.androiddevs.ktornoteapp.data.local.NoteDao
import com.androiddevs.ktornoteapp.data.local.NotesDatabase
import com.androiddevs.ktornoteapp.data.remote.BasicAuthInterceptor
import com.androiddevs.ktornoteapp.data.remote.api.AccountApi
import com.androiddevs.ktornoteapp.data.remote.api.NoteApi
import com.androiddevs.ktornoteapp.utils.Constants.BASE_URL
import com.androiddevs.ktornoteapp.utils.Constants.ENCRYPTED_SHARED_PREF_NAME
import com.androiddevs.ktornoteapp.utils.Constants.NOTES_DATABASE_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
// Says the component will live while the app lives.
// All dependencies created here lives while the app lives
object AppModule {

    @Provides
    @Singleton // one single class of this type will be instantiated at this component
    fun providesBasicAuthInterceptor(): BasicAuthInterceptor {
        return BasicAuthInterceptor()
    }

    @Provides
    @Singleton
    fun providesGsonConverterFactory(): GsonConverterFactory {
        return GsonConverterFactory.create()
    }

    @Provides
    @Singleton
    fun providesOkHttpClient(
        interceptor: BasicAuthInterceptor,
    ): OkHttpClient {
        return OkHttpClient.Builder().addInterceptor(interceptor).build()
    }

    @Provides
    @Singleton
    fun providesRetrofit(
        okHttpClient: OkHttpClient,
        converterFactory: GsonConverterFactory,
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(converterFactory)
            .build()
    }

    @Provides
    @Singleton
    fun providesEncryptedSharedPreferences(
        @ApplicationContext context: Context
    ): SharedPreferences {
        val masterKey = MasterKey.Builder(context)
            .setKeyScheme(MasterKey.KeyScheme.AES256_GCM)
            .build()
        return EncryptedSharedPreferences.create(
            context,
            ENCRYPTED_SHARED_PREF_NAME,
            masterKey,
            EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
            EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM,
        )
    }

    @Provides
    @Singleton
    fun providesNoteApi(retrofit: Retrofit): NoteApi = retrofit.create(NoteApi::class.java)

    @Provides
    @Singleton
    fun providesAccountApi(retrofit: Retrofit): AccountApi = retrofit.create(AccountApi::class.java)

    @Provides
    @Singleton
    fun providesNotesDatabase(
        @ApplicationContext context: Context
    ): NotesDatabase =
        Room.databaseBuilder(context, NotesDatabase::class.java, NOTES_DATABASE_NAME).build()

    @Provides
    @Singleton
    fun providesNotesDao(notesDatabase: NotesDatabase): NoteDao = notesDatabase.noteDao()
}