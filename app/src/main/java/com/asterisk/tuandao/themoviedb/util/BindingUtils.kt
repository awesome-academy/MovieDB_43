package com.asterisk.tuandao.themoviedb.util

import android.annotation.SuppressLint
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.asterisk.tuandao.themoviedb.R
import com.asterisk.tuandao.themoviedb.data.source.model.Genre
import com.asterisk.tuandao.themoviedb.data.source.model.Movie
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions

@BindingAdapter("imageUrl")
fun ImageView.setImage(url: String?) {
    if (url == null) {
        setBackgroundColor(resources.getColor(R.color.placeholder_bg))
    } else {
        val requestOptions = RequestOptions().apply {
            placeholder(R.color.placeholder_bg)
            error(R.color.placeholder_bg)
        }
        val imageLink = StringUtils.getImageLink(Constants.IMAGE_SIZE_200, url!!)
        Glide.with(this.context)
            .load(imageLink)
            .apply(requestOptions)
            .transition(DrawableTransitionOptions.withCrossFade(500))
            .into(this)
    }
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
    if (placeOfBirthday != null) {
        text = "${Constants.DEATH_DAY_TITLE} $placeOfBirthday"
    } else {
        text = "${Constants.DEATH_DAY_TITLE} ${Constants.NOT_YET}"
    }
}

@BindingAdapter("imageResource")
fun ImageView.setImageGenre(genre: String) {
    val resourceId = StringUtils.getDrawableId(genre)
    Glide.with(this.context).load(resourceId)
        .transition(DrawableTransitionOptions.withCrossFade(1000))
        .into(this)
}

@BindingAdapter("bindDuration")
fun bindDuration(textView: TextView, runtime: Int) {
    textView.text = StringUtils.getDuration(runtime)
}

@BindingAdapter("bindGenres")
fun bindGenres(textView: TextView, genres: List<Genre>?) {
    if (genres == null) return
    if (genres != null) {
        textView.text = StringUtils.getGenres(genres)
        textView.isSelected = true
    }
}

@BindingAdapter("bindDeadDay")
fun bindDeadDay(textView: TextView, text: String?) {
    if (text == null) {
        textView.text = Constants.NOT_YET
        return
    }
    textView.text = text
}
