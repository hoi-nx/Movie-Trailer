package com.sun.moviedb.data.dto

import com.google.gson.annotations.SerializedName
import com.sun.moviedb.data.entity.Genres

/**
 * Created by nguyenxuanhoi on 2019-08-14.
 * @author nguyen.xuan.hoi@sun-asterisk.com
 */
data class GenresDto(@SerializedName("genres") val list: List<Genres>)
