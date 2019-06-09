package com.asterisk.tuandao.themoviedb.util

object StringUtils {
    fun append(vararg strings: String): String {
        val builder = StringBuilder()
        for (string in strings) {
            builder.append(string)
        }
        return builder.toString()
    }

    fun getImage(image_path: String?): String? {
        if (image_path == null) return null
        val builder = StringBuilder()
        builder.append(Constants.BASE_IMAGE_PATH)
                .append(Constants.IMAGE_SIZE_W500)
                .append(image_path)
        return builder.toString()
    }

    fun getImageLink(size: Int, url: String) = append(Constants.IMAGE_LINK, size.toString(), Constants.SLASH, url)
}
