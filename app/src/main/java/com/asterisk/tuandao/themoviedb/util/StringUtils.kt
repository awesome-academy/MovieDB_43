package com.asterisk.tuandao.themoviedb.util

object StringUtils {
    fun append(vararg strings: String): String {
        val builder = StringBuilder()
        for (string in strings) {
            builder.append(string)
        }
        return builder.toString()
    }

    fun getImageLink(size: Int, url: String) = append(Constants.IMAGE_LINK, size.toString(), Constants.SLASH, url)
}
