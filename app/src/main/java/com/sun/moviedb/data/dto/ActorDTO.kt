package com.sun.moviedb.data.dto

/**
 * Created by nguyenxuanhoi on 2019-08-29.
 * @author nguyen.xuan.hoi@sun-asterisk.com
 */
data class ActorDTO(
    val biography: String,
    val birthday: String,
    val id: Int,
    val name: String,
    val place_of_birth: String,
    val popularity: Float,
    val profile_path: String
)