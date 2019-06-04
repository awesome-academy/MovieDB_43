package com.asterisk.tuandao.themoviedb.util

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.asterisk.tuandao.themoviedb.R
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

@BindingAdapter("imageUrl")
fun ImageView.setImage(url: String?) {
    val requestOptions = RequestOptions().apply {
        placeholder(R.drawable.ic_loading)
        error(R.drawable.ic_loading)
    }
    val imageLink = StringUtils.getImageLink(Constants.IMAGE_SIZE_200, url!!)
    Glide.with(this.context)
        .load(imageLink)
        .apply(requestOptions)
        .into(this)
}
