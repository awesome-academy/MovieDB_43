package com.asterisk.tuandao.themoviedb.util

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.asterisk.tuandao.themoviedb.R
import com.asterisk.tuandao.themoviedb.util.Constants.IMAGE_SIZE_200
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

@BindingAdapter("imageUrl")
fun ImageView.setImage(url: String?) {
    if (url == null) {
        setImageResource(R.drawable.ic_loading)
    } else {
        val requestOptions = RequestOptions().apply {
            placeholder(R.drawable.ic_loading)
            error(R.drawable.ic_loading)
        }
        val imageLink = StringUtils.getImageLink(Constants.IMAGE_SIZE_200, url)
        Glide.with(this.context)
            .load(imageLink)
            .apply(requestOptions)
            .into(this)
    }
//    Glide.with(this)
//            .load(StringUtils.getImage(url))
//            .error(R.drawable.ic_loading)
//            .into(this)
//    if (url == null || url.isEmpty()) {
//        setImageResource(R.drawable.ic_loading)
//    }
//    val requestOptions = RequestOptions()
//    requestOptions.placeholder(R.drawable.ic_loading)
//    requestOptions.error(R.drawable.ic_loading)
//    val imageLink = StringUtils.getImageLink(IMAGE_SIZE_200, url!!)
//    Glide.with(context)
//            .load(imageLink)
//            .apply(requestOptions)
//            .into(this)
//    if (url == null || url.isEmpty()) {
//        setImageResource(R.drawable.ic_loading)
//    }
//    val imageLink = StringUtils.getImageLink(IMAGE_SIZE_200, url)
//    val requestOptions = RequestOptions()
//    requestOptions.placeholder(R.drawable.ic_loading)
//    requestOptions.error(R.drawable.ic_loading)
//    Glide.with(context)
//        .load(imageLink)
//        .apply(requestOptions.circleCrop().transform())
//        .into(imageView)
}

@BindingAdapter("setTextGender")
fun TextView.setGender(gender: Int) {
    when (gender) {
        Constants.GENDER_FAMALE -> setText(R.string.genre_female)
        else -> setText(R.string.genre_male)
    }
}

@BindingAdapter("setTextBirthday")
fun TextView.setBirthday(birthday: String?) {
    val born = "${Constants.BIRTHDAY_TITLE} $birthday"
    text = born
}

@BindingAdapter("setTextPlaceOfBirth")
fun TextView.setTextPlaceOfBirth(placeOfBirthday: String?) {
    val placeOfBirth = "${Constants.PLACE_OF_BIRTHDAY_TITLE} $placeOfBirthday"
    text = placeOfBirth
}

@BindingAdapter("setTextDeathDay")
fun TextView.setTextDeathDay(placeOfBirthday: String?) {
    if (placeOfBirthday!=null) {
        text = "${Constants.DEATH_DAY_TITLE} $placeOfBirthday"
    } else {
        text = "${Constants.DEATH_DAY_TITLE}"
    }
}
