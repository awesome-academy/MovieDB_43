package com.asterisk.tuandao.themoviedb.data.source.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Review {
    @SerializedName("author")
    @Expose
    val author: String? = null

    @SerializedName("content")
    @Expose
    val content: String? = null

    @SerializedName("id")
    @Expose
    val id: String? = null

    @SerializedName("url")
    @Expose
    val url: String? = null
}
