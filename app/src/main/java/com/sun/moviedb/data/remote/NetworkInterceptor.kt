package com.sun.moviedb.data.remote

import com.sun.moviedb.MovieApplication
import com.sun.moviedb.R
import com.sun.moviedb.exceptions.NetworkException
import com.sun.moviedb.utils.NetworkUtil
import okhttp3.Interceptor
import okhttp3.Response

/**
 * Created by nguyenxuanhoi on 2019-07-17.
 * @author nguyen.xuan.hoi@sun-asterisk.com
 */
class NetworkInterceptor : Interceptor {

    @Throws(NetworkException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        if (!NetworkUtil.isNetworkAvailable()) {
            throw NetworkException(MovieApplication.applicationContext!!.getString(R.string.error_connect))
        }
        return chain.proceed(chain.request())
    }

    companion object {
        fun getInstance() = NetworkInterceptor()
    }
}
