package com.dicoding.firstsubmissionmoviecatalogue.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class ResponseList<T>(
    @SerializedName("status_message")
    val statusMessage: String? =null,
    @SerializedName("status_code")
    val statusCode: String? = null,
    @SerializedName("result")
    val result:List<T>? = null
) {
}