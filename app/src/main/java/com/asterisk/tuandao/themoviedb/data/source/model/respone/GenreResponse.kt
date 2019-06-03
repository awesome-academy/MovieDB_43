package com.asterisk.tuandao.themoviedb.data.source.model.respone

import com.asterisk.tuandao.themoviedb.data.source.model.Genre
import com.google.gson.annotations.SerializedName

class GenreResponse {
    @SerializedName("genres")
    val genres: List<Genre>? = null
}
