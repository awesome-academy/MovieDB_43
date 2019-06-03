package com.asterisk.tuandao.themoviedb.data.source.model

import com.asterisk.tuandao.themoviedb.data.source.model.respone.GenreResponse
import com.google.gson.annotations.SerializedName

class Movie {
    @SerializedName("id")
    val id: Int = 0

    @SerializedName("backdrop_path")
    val backdropPath: String? = null

    @SerializedName("genres")
    val genres: List<GenreResponse>? = null

    @SerializedName("title")
    val title: String? = null

    @SerializedName("overview")
    val overview: String? = null

    @SerializedName("poster_path")
    val posterPath: String? = null

    @SerializedName("production_companies")
    val productionCompanies: List<ProductionCompany>? = null

    @SerializedName("release_date")
    val releaseDate: String? = null

    @SerializedName("runtime")
    val runtime: Int = 0

    @SerializedName("status")
    val status: String? = null

    @SerializedName("vote_average")
    val voteAverage: Double = 0.0
}
