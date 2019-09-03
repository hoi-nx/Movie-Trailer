package com.sun.moviedb.utils

import android.content.Context
import android.net.ConnectivityManager
import com.sun.moviedb.MovieApplication

/**
 * Created by nguyenxuanhoi on 2019-07-17.
 * @author nguyen.xuan.hoi@sun-asterisk.com
 */
object NetworkUtil {
    fun isNetworkAvailable(): Boolean {
        val manager = MovieApplication.applicationContext?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        return manager.activeNetworkInfo?.isConnected ?: false
    }
}
