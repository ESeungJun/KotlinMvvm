package com.project.network

import android.os.Build
import com.google.gson.GsonBuilder
import okhttp3.*
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.net.ssl.SSLContext


object RetrofitClient{

    private const val CONNECT_TIMEOUT = 10L
    private const val WRITE_TIMEOUT = 10L
    private const val READ_TIMEOUT = 10L


    private val client by lazy {
        OkHttpClient.Builder().apply {
            addInterceptor(HttpLoggingInterceptor().apply {
                connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS)
                writeTimeout(WRITE_TIMEOUT, TimeUnit.SECONDS)
                readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
                if (BuildConfig.DEBUG)
                    level = HttpLoggingInterceptor.Level.BODY
            })

            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP_MR1) {

                try {
                    val sslContext = SSLContext.getInstance("TLSv1.2").apply {
                        init(null, null, null)
                    }

                    sslSocketFactory(TLSSocketFactory(sslContext.socketFactory))

                    val cs = ConnectionSpec.Builder(ConnectionSpec.MODERN_TLS)
                        .tlsVersions(TlsVersion.TLS_1_2)
                        .build()

                    val specs: MutableList<ConnectionSpec> = mutableListOf()
                    specs.add(cs)
                    specs.add(ConnectionSpec.COMPATIBLE_TLS)
                    specs.add(ConnectionSpec.CLEARTEXT)

                    connectionSpecs(specs)

                } catch (e: Exception){
                    e.printStackTrace()
                }

            }

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

    fun build() = Retrofit.Builder().apply {
        addConverterFactory(GsonConverterFactory.create(gson))
        addCallAdapterFactory(RxJava2CallAdapterFactory.createAsync())
        baseUrl("https://api.github.com/")
        client(client)
    }.build()
}
