package com.asterisk.tuandao.themoviedb.data.source.model

import com.asterisk.tuandao.themoviedb.data.source.model.respone.CastResult
import com.asterisk.tuandao.themoviedb.data.source.model.respone.VideoResult
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Movie {
    @SerializedName("id")
    @Expose
    val id: Int = 0

    @SerializedName("backdrop_path")
    @Expose
    val backdropPath: String? = null

    @SerializedName("genres")
    @Expose
    val genres: List<Genre>? = null

    @SerializedName("title")
    @Expose
    val title: String? = null

    @SerializedName("overview")
    @Expose
    val overview: String? = null

    @SerializedName("poster_path")
    @Expose
    val posterPath: String? = null

    @SerializedName("production_companies")
    @Expose
    val productionCompanies: List<Company>? = null

    @SerializedName("release_date")
    @Expose
    val releaseDate: String? = null

    @SerializedName("runtime")
    @Expose
    val runtime: Int = 0

    @SerializedName("spoken_languages")
    @Expose
    val spokenLanguages: List<Language>? = null

    @SerializedName("vote_average")
    @Expose
    val voteAverage: Float = 0.toFloat()

    @SerializedName("videos")
    @Expose
    val videoResult: VideoResult? = null

    @SerializedName("credits")
    @Expose
    val castResult: CastResult? = null
}
