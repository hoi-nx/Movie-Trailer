package com.sun.moviedb.data.dto

import com.sun.moviedb.data.entity.Movie

/**
 * Created by nguyenxuanhoi on 2019-08-15.
 * @author nguyen.xuan.hoi@sun-asterisk.com
 */
data class CategoryDTO(val queryType: String, var movies: List<Movie> = emptyList())
