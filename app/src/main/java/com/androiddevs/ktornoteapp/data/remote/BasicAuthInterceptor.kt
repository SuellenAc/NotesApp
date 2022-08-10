package com.androiddevs.ktornoteapp.data.remote

import okhttp3.Credentials
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import retrofit2.Invocation

private const val AUTHORIZATION_HEADER = "Authorization"

class BasicAuthInterceptor : Interceptor {

    private val email: String? = null
    private val password: String? = null

    override fun intercept(chain: Interceptor.Chain): Response {
        return if (chain.request().hasCustomAnnotation(Authenticated::class.java)) {
            val request = chain.request()
                .newBuilder()
                .addHeader(AUTHORIZATION_HEADER, getCredentials())
                .build()
            chain.proceed(request)
        } else {
            chain.proceed(chain.request())
        }
    }

    private fun getCredentials() = Credentials.basic(
        username = email.orEmpty(),
        password = password.orEmpty()
    )

    private fun <T : Annotation> Request.hasCustomAnnotation(annotationClass: Class<T>): Boolean =
        this.tag(Invocation::class.java)?.method()?.getAnnotation(annotationClass) != null
}