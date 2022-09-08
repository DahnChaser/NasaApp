package com.dev.networking_library

import java.util.*
import java.util.concurrent.TimeUnit

object RestClient {

    const val CONNECTION_TIMEOUT = 1200000L

    var spec: okhttp3.ConnectionSpec = okhttp3.ConnectionSpec.Builder(okhttp3.ConnectionSpec.Companion.COMPATIBLE_TLS)
        .tlsVersions(okhttp3.TlsVersion.TLS_1_3, okhttp3.TlsVersion.TLS_1_2)
        .cipherSuites(
            okhttp3.CipherSuite.Companion.TLS_ECDHE_ECDSA_WITH_AES_128_GCM_SHA256,
            okhttp3.CipherSuite.Companion.TLS_ECDHE_RSA_WITH_AES_128_GCM_SHA256,
            okhttp3.CipherSuite.Companion.TLS_DHE_RSA_WITH_AES_128_GCM_SHA256,
            okhttp3.CipherSuite.Companion.TLS_ECDHE_RSA_WITH_AES_128_CBC_SHA,
            okhttp3.CipherSuite.Companion.TLS_ECDHE_RSA_WITH_AES_128_GCM_SHA256
        )
        .build()

    private fun okHttpInterceptor(logRequests: Boolean): okhttp3.OkHttpClient {

        val loggingIntecerptor = okhttp3.logging.HttpLoggingInterceptor()
        if(logRequests)
        {
            loggingIntecerptor.level = okhttp3.logging.HttpLoggingInterceptor.Level.BODY
        }
        else
        {
            loggingIntecerptor.level = okhttp3.logging.HttpLoggingInterceptor.Level.NONE

        }


        val client =
            okhttp3.OkHttpClient.Builder()
                .connectionSpecs(listOf(spec))
                .readTimeout(CONNECTION_TIMEOUT, TimeUnit.SECONDS)
                .connectTimeout(CONNECTION_TIMEOUT, TimeUnit.SECONDS)
                .connectionPool(okhttp3.ConnectionPool(0, 5, TimeUnit.MINUTES))
                .protocols(listOf(okhttp3.Protocol.HTTP_1_1))
                .addInterceptor(loggingIntecerptor)
                .connectionSpecs(Arrays.asList(okhttp3.ConnectionSpec.Companion.COMPATIBLE_TLS))
                .build()

        return client
    }




    open fun mRetrofitInstance(mBaseUrl:String,logRequests:Boolean) : retrofit2.Retrofit = retrofit2.Retrofit.Builder()
        .baseUrl(mBaseUrl)
        .addConverterFactory(retrofit2.converter.gson.GsonConverterFactory.create())
        .addCallAdapterFactory(com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory())
        .client(okHttpInterceptor(logRequests))
        .build()



}