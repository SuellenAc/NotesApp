package com.androiddevs.ktornoteapp.di

import com.androiddevs.ktornoteapp.data.remote.BasicAuthInterceptor
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
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
    fun providesBasicAuthInterceptor() : BasicAuthInterceptor {
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
    ) : OkHttpClient {
        return OkHttpClient.Builder().addInterceptor(interceptor).build()
    }

    @Provides
    @Singleton
    fun providesRetrofit(
        okHttpClient: OkHttpClient,
        converterFactory: GsonConverterFactory,
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl("localhost:8001")
            .client(okHttpClient)
            .addConverterFactory(converterFactory)
            .build()
    }
}