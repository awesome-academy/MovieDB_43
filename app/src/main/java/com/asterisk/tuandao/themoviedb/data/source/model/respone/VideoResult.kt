package com.asterisk.tuandao.themoviedb.data.source.model.respone

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName



class VideoResult {
    @SerializedName("id")
    @Expose
    val mId: String? = null
    @SerializedName("iso_639_1")
    @Expose
    val mIso639: String? = null
    @SerializedName("iso_3166_1")
    @Expose
    val mIso3166: String? = null
    @SerializedName("key")
    @Expose
    val mKey: String? = null
    @SerializedName("name")
    @Expose
    val mName: String? = null
    @SerializedName("site")
    @Expose
    val mSite: String? = null
    @SerializedName("size")
    @Expose
    val mSize: Int = 0
    @SerializedName("type")
    @Expose
    private val mType: String? = null
}