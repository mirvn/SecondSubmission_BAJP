package com.dicoding.firstsubmissionmoviecatalogue.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class MovieResponse(
    @SerializedName("id")
    var id: Int = 0,
    @SerializedName("title")
    var name: String? = null,
    @SerializedName("overview")
    var description: String? = null,
    @SerializedName("poster_path")
    var poster: String? = null,
    @SerializedName("backdrop_path")
    var image: String? = null
) {
}