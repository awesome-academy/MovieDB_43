package com.asterisk.tuandao.themoviedb.data.source.model

import com.google.gson.annotations.SerializedName

class Genre {
    @SerializedName("id")
    var id: String? = null

    @SerializedName("name")
    var name: String? = null
}