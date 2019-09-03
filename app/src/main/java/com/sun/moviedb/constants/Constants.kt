package com.sun.moviedb.constants

interface Constants {
    companion object {
        val ARGUMENT_VIDEO = "video"
        val ARGUMENT_MOVIE_DETAIL = "movie_detail"
        val ARGUMENT_CAST = "cast"
        const val TIME_DELAY_CHANGE_MODE = 500L
        const val TIME_DELAY = 3000L
        val BASE_IMAGE_PATH = "https://image.tmdb.org/t/p/"
        val IMAGE_SIZE_W200 = "w200/"
        val IMAGE_SIZE_W500 = "w500/"
        val BASE_THUMBNAIL_PATH = "https://img.youtube.com/vi/%s/hqdefault.jpg"
        val BASE_YOUTUBE= "https://www.youtube.com/watch?v=%s"
        const val BUNDLE_QUERY = "query"
    }
}
