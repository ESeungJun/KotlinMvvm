package com.project.network

import com.google.gson.GsonBuilder
import okhttp3.Headers
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


object RetrofitClient{

    const val CONNECT_TIMEOUT = 10L
    const val WRITE_TIMEOUT = 10L
    const val READ_TIMEOUT = 10L


    private val client by lazy {
        OkHttpClient.Builder().apply {
            addInterceptor(HttpLoggingInterceptor().apply {
                connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS)
                writeTimeout(WRITE_TIMEOUT, TimeUnit.SECONDS)
                readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
                if (BuildConfig.DEBUG)
                    level = HttpLoggingInterceptor.Level.BODY
            })

//            addInterceptor{ chain -> createHeaderChain(chain) }
        }.build()
    }

    private val gson by lazy {
        GsonBuilder().apply {
            setLenient()
        }.create()
    }


    private var baseHeader: Map<String, String>? = null


    private fun createBaseHeader() {
        if(baseHeader == null)
            baseHeader = hashMapOf<String, String>()
                .also {}
    }


    // 헤더 셋팅시에만 사용
    private fun createHeaderChain(chain: Interceptor.Chain): Response {
        val request = chain.request()

        createBaseHeader()

        return chain.proceed(request.newBuilder()
            .headers(Headers.of(baseHeader.toString()))
            .method(request.method(), request.body())
            .build())
    }

    private fun buildClient() = Retrofit.Builder().apply {
        addConverterFactory(GsonConverterFactory.create(gson))
        addCallAdapterFactory(RxJava2CallAdapterFactory.createAsync())
        baseUrl("http://54.180.6.192:8080/randomOX/")
        client(client)
    }.build()



    fun build() = buildClient()
}
