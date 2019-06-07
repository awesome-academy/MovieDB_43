package com.asterisk.tuandao.themoviedb.data.source.model.respone

import com.asterisk.tuandao.themoviedb.data.source.model.Actor
import com.asterisk.tuandao.themoviedb.data.source.model.Movie
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class ActorResponse {
    @SerializedName("id")
    @Expose
    val id: Int = 0

    @SerializedName("name")
    @Expose
    val name: String? = null

    @SerializedName("birthday")
    @Expose
    val birthday: String? = null

    @SerializedName("movie_credits")
    @Expose
    val movies: List<Movie>? = null

    @SerializedName("deathday")
    @Expose
    val deathday: String?=null

    @SerializedName("place_of_birth")
    @Expose
    val placeOfBirth: String?=null

    @SerializedName("profile_path")
    @Expose
    val profilePath: String?=null

    @SerializedName("gender")
    @Expose
    val gender: Int=0
}
