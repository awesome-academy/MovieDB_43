package com.asterisk.tuandao.themoviedb.util

object Constants {
    //maxium time request 5s
    const val MAXIMUM_REQUEST_TIMEOUT = 5
    // maxium tim cache 1 day
    const val MAXIMUM_CACHE_TIME = 60 * 60 * 24 * 1
    // cache size 5mb
    const val CACHE_SIZE = (5 * 1024 * 1024).toLong()
    //uri image link
    const val IMAGE_LINK = "https://image.tmdb.org/t/p/w"
    // slash create url image
    const val SLASH = "/"
    // size default image
    const val IMAGE_SIZE_200 = 1280
    //number of items displaying home
    const val SPAN_COUNT = 2
    //genre id tag
    const val GENRE_ID_TAG = "genre_id"
    //default page
    const val DEFAULT_PAGE = 1
}
