package com.sun.moviedb.utils

import com.sun.moviedb.constants.Constants

/**
 * Created by nguyenxuanhoi on 2019-08-16.
 * @author nguyen.xuan.hoi@sun-asterisk.com
 */
object StringUtils {
    fun getImage(image_path: String): String {
        val builder = StringBuilder()
        builder.append(Constants.BASE_IMAGE_PATH)
            .append(Constants.IMAGE_SIZE_W500)
            .append(image_path)
        return builder.toString()
    }
    fun getImageW200(image_path: String): String {
        val builder = StringBuilder()
        builder.append(Constants.BASE_IMAGE_PATH)
            .append(Constants.IMAGE_SIZE_W200)
            .append(image_path)
        return builder.toString()
    }
    fun concateString(vararg strings: String): String {
        val stringBuilder = StringBuilder()
        for (s in strings) {
            stringBuilder.append(s)
        }
        return stringBuilder.toString()
    }
    fun getThumbnail(trailerKey: String): String {
        return String.format(Constants.BASE_THUMBNAIL_PATH, trailerKey)
    }
    fun getUrlYoutube(trailerKey: String): String {
        return String.format(Constants.BASE_YOUTUBE, trailerKey)
    }
}

