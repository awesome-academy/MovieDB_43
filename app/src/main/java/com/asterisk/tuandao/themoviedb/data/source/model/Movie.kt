package com.asterisk.tuandao.themoviedb.data.source.model

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.asterisk.tuandao.themoviedb.data.source.model.respone.CastResult
import com.asterisk.tuandao.themoviedb.data.source.model.respone.ReviewResult
import com.asterisk.tuandao.themoviedb.data.source.model.respone.VideoResult
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

@Entity(tableName = "movie")
class Movie {
    @PrimaryKey
    @SerializedName("id")
    @Expose
    var id: Int = 0

    @SerializedName("backdrop_path")
    @Expose
    var backdropPath: String? = null

    @Ignore
    @SerializedName("genres")
    @Expose
    val genres: List<Genre>? = null

    @SerializedName("title")
    @Expose
    var title: String? = null

    @SerializedName("overview")
    @Expose
    var overview: String? = null

    @SerializedName("poster_path")
    @Expose
    var posterPath: String? = null

    @Ignore
    @SerializedName("production_companies")
    @Expose
    val productionCompanies: List<Company>? = null

    @SerializedName("release_date")
    @Expose
    var releaseDate: String? = null

    @SerializedName("runtime")
    @Expose
    var runtime: Int = 0

    @Ignore
    @SerializedName("spoken_languages")
    @Expose
    val spokenLanguages: List<Language>? = null

    @SerializedName("vote_average")
    @Expose
    var voteAverage: Float = 0.0f

    @Ignore
    @SerializedName("videos")
    @Expose
    val videoResult: VideoResult? = null

    @Ignore
    @SerializedName("credits")
    @Expose
    val castResult: CastResult? = null

    @Ignore
    @SerializedName("reviews")
    @Expose
    val reviewResult: ReviewResult? = null
}
