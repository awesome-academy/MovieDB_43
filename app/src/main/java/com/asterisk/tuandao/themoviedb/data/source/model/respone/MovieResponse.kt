package com.asterisk.tuandao.themoviedb.data.source.model.respone

import com.asterisk.tuandao.themoviedb.data.source.model.Movie
import com.google.gson.annotations.SerializedName

class MovieResponse {
    @SerializedName("page")
    val page: Int = 0

    @SerializedName("total_results")
    val totalResults: Int = 0

    @SerializedName("total_pages")
    val totalPage: Int = 0

    @SerializedName("results")
    val results: List<Movie>? = null
}
