package com.project.seungjun.model.network

import com.project.network.RetrofitClient
import com.project.seungjun.model.network.api.TestApiService

object ApiController {

    val testApiService
        get() = RetrofitClient.build().create(TestApiService::class.java)

}