package com.asterisk.tuandao.themoviedb.data.source.model.respone

import com.asterisk.tuandao.themoviedb.data.source.model.Actor
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class CastResult {
    @SerializedName("cast")
    @Expose
    val casts: List<Actor>? = null
}
