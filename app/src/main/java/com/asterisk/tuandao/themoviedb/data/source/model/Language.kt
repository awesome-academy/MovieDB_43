package com.asterisk.tuandao.themoviedb.data.source.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Language {
    @SerializedName("iso_639_1")
    @Expose
    val iso639: String? = null
    @SerializedName("name")
    @Expose
    val name: String? = null
}
