package com.sun.moviedb.utils

import com.sun.moviedb.MovieApplication
import com.sun.moviedb.BuildConfig
import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by nguyenxuanhoi on 2019-08-28.
 * @author nguyen.xuan.hoi@sun-asterisk.com
 */

object DeviceUtil {
    fun getVersionApp(): String {
        return BuildConfig.VERSION_NAME
    }

    fun getVersionData(): String {
        return BuildConfig.APP_DATABASE_VERSION.toString()
    }

    fun getBuildTime(pattern: String): String {
        val locale = MovieApplication.applicationContext!!.resources.configuration.locale
        val simpleDateFormat = SimpleDateFormat(pattern, locale)
        return simpleDateFormat.format(Date(BuildConfig.BUILD_TIME))
    }
}