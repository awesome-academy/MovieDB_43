package com.asterisk.tuandao.themoviedb.data.source.model.respone

import com.asterisk.tuandao.themoviedb.data.source.model.Video
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class VideoResult {
    @SerializedName("results")
    @Expose
    val videos: List<Video>? = null
}
