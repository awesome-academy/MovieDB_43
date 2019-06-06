package com.asterisk.tuandao.themoviedb.data.source.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName



class Company {
    @SerializedName("id")
    @Expose
    val id: Int = 0
    @SerializedName("logo_path")
    @Expose
    val logoPath: String? = null
    @SerializedName("name")
    @Expose
    val name: String? = null
    @SerializedName("origin_country")
    @Expose
    val origionCountry: String? = null
}
