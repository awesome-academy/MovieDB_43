package com.asterisk.tuandao.themoviedb.data.source.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class MovieCredit {
    @SerializedName("cast")
    @Expose
    val movies: List<Movie>? = null
}
