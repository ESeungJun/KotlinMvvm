package com.project.seungjun.model.network.api

import com.project.network.response.BaseApiResponse
import io.reactivex.Single
import retrofit2.http.*


interface TestApiService {

    @POST("")
    fun testPost(@Body body: Any): Single<BaseApiResponse>

    @GET("search/users?q=a&page=1&per_page=2")
    fun testGet(): Single<BaseApiResponse>

    @GET("{id}")
    fun testGet(@Path("id") groupId: Int, @Query("sort") sort: String, @QueryMap options: Map<String, String>): Single<BaseApiResponse>
}
