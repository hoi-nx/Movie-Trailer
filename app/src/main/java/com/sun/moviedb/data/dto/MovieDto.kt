package com.sun.moviedb.data.dto

import com.google.gson.annotations.SerializedName
import com.sun.moviedb.data.entity.Movie

/**
 * Created by nguyenxuanhoi on 2019-08-14.
 * @author nguyen.xuan.hoi@sun-asterisk.com
 */
data class MovieDto(@SerializedName("results") val result: List<Movie>)
