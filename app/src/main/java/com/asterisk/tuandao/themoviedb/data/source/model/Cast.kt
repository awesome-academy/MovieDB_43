package com.asterisk.tuandao.themoviedb.data.source.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Cast {
    @SerializedName("id")
    @Expose
    val id: Int = 0

    @SerializedName("vote_average")
    @Expose
    val voteAverage: Float = 0.0f

    @SerializedName("title")
    @Expose
    val title: String? = null
}
