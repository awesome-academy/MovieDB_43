package com.asterisk.tuandao.themoviedb.util

import com.asterisk.tuandao.themoviedb.R
import com.asterisk.tuandao.themoviedb.data.source.model.Genre
import com.asterisk.tuandao.themoviedb.data.source.model.Movie

object StringUtils {
    const val ACTION_TAG = "Action"
    const val ADVENTURE_TAG ="Adventure"
    const val ANIMATION_TAG = "Animation"
    const val COMEDY_TAG = "Comedy"
    const val CRIME_TAG = "Crime"
    const val DOCUMENTARY_TAG = "Documentary"
    const val DRAME_TAG = "Drama"
    const val FAMILY_TAG = "Family"
    const val FANTASY_TAG = "Fantasy"
    const val HISTORY_TAG = "History"
    const val HORROR_TAG = "Horror"
    const val MUSIC_TAG = "Music"
    const val MYSTERY_TAG = "Mystery"
    const val ROMANCE_TAG = "Romance"
    const val SCIENCE_FICTION_TAG = "Science Fiction"
    const val TV_MOVIE_TAG = "TV Movie"
    const val THRILLER_TAG = "Thriller"
    const val WAR_TAG = "War"
    const val WESTERN_TAG = "Western"
    const val NOW_PLAYING_TAG = "Now Playing"
    const val TOP_RATE_TAG = "Top Rate"
    const val UP_COMMING = "Up Coming"
    const val MINUTES = " minutes"
    private const val HYPHEN = " - "
    fun append(vararg strings: String): String {
        val builder = StringBuilder()
        for (string in strings) {
            builder.append(string)
        }
        return builder.toString()
    }

    fun getImageLink(size: Int, url: String) = append(Constants.IMAGE_LINK, size.toString(), Constants.SLASH, url)

    fun getDrawableId(genre: String): Int{
        return when(genre) {
            ACTION_TAG -> R.drawable.action
            ADVENTURE_TAG -> R.drawable.adventure
            CRIME_TAG -> R.drawable.crime
            DOCUMENTARY_TAG -> R.drawable.documentary
            DRAME_TAG -> R.drawable.drama
            FAMILY_TAG -> R.drawable.family
            FANTASY_TAG -> R.drawable.fantasy
            HISTORY_TAG -> R.drawable.history
            HORROR_TAG -> R.drawable.horror
            MUSIC_TAG -> R.drawable.music
            MYSTERY_TAG -> R.drawable.mystery
            ROMANCE_TAG -> R.drawable.romance
            SCIENCE_FICTION_TAG -> R.drawable.science
            TV_MOVIE_TAG -> R.drawable.tvmovie
            THRILLER_TAG -> R.drawable.thriller
            WAR_TAG -> R.drawable.war
            ANIMATION_TAG -> R.drawable.animation
            COMEDY_TAG -> R.drawable.comedy
            NOW_PLAYING_TAG -> R.drawable.nowplaying
            TOP_RATE_TAG -> R.drawable.toprate
            UP_COMMING -> R.drawable.upcoming
            else -> R.drawable.western
        }
    }

    fun getDuration(runtime: Int): String {
        val builder = StringBuilder()
        builder.append(runtime).append(MINUTES)
        return builder.toString()
    }

    fun getGenres(genres: List<Genre>): String {
        val builder = StringBuilder()
        for (genre in genres) {
            builder.append(genre.name)
            if (!genre.equals(genres.last()))
                builder.append(HYPHEN)
        }
        return builder.toString()
    }
}
