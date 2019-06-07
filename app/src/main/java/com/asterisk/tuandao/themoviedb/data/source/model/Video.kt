package com.asterisk.tuandao.themoviedb.data.source.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Video {
    @SerializedName("id")
    @Expose
    val id: String? = null
    @SerializedName("iso_639_1")
    @Expose
    val iso639: String? = null
    @SerializedName("iso_3166_1")
    @Expose
    val iso3166: String? = null
    @SerializedName("key")
    @Expose
    val key: String? = null
    @SerializedName("name")
    @Expose
    val name: String? = null
    @SerializedName("site")
    @Expose
    val site: String? = null
    @SerializedName("size")
    @Expose
    val size: Int = 0
    @SerializedName("type")
    @Expose
    val type: String? = null
}
