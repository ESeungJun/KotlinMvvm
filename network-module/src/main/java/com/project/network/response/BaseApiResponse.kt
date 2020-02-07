package com.project.network.response

import com.google.gson.annotations.SerializedName

data class BaseApiResponse (
    @SerializedName("")
    val code: Int,

    @SerializedName("")
    val message: String
)