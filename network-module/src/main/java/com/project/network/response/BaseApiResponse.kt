package com.project.network.response

import com.google.gson.annotations.SerializedName

open class BaseApiResponse {

    @SerializedName("code")
    val code: Int = 0

    @SerializedName("message")
    val message: String = ""
}