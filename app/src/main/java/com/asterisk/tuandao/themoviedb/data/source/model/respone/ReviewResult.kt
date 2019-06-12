package com.asterisk.tuandao.themoviedb.data.source.model.respone

import com.asterisk.tuandao.themoviedb.data.source.model.Review
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class ReviewResult {
    @SerializedName("page")
    @Expose
    val page: Int = 0

    @SerializedName("results")
    @Expose
    val results: List<Review>? = null

    @SerializedName("total_pages")
    @Expose
    val totalPage: Int = 0

    @SerializedName("total_results")
    @Expose
    val totalResult: Int = 0
}
