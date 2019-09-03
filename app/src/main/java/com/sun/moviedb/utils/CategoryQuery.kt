package com.sun.moviedb.utils

import androidx.annotation.StringDef

/**
 * Created by nguyenxuanhoi on 2019-08-15.
 * @author nguyen.xuan.hoi@sun-asterisk.com
 */
@StringDef(
    CategoryQuery.NOW_PLAYING,
    CategoryQuery.POPULAR,
    CategoryQuery.UP_COMING,
    CategoryQuery.TOP_RATE
)
annotation class CategoryQuery {
    companion object {
        const val NOW_PLAYING = "now_playing"
        const val POPULAR = "popular"
        const val UP_COMING = "upcoming"
        const val TOP_RATE = "top_rated"
    }
}
