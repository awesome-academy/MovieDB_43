package com.asterisk.tuandao.themoviedb.data.source.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Actor {
    @SerializedName("cast_id")
    @Expose
    val castId: Int = 0
    @SerializedName("character")
    @Expose
    val character: String? = null
    @SerializedName("credit_id")
    @Expose
    val creditId: String? = null
    @SerializedName("id")
    @Expose
    val id: Int = 0
    @SerializedName("name")
    @Expose
    val name: String? = null
    @SerializedName("profile_path")
    @Expose
    val profilePath: String? = null
}
