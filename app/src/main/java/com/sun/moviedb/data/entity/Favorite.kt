package com.sun.moviedb.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Created by nguyenxuanhoi on 2019-08-26.
 * @author nguyen.xuan.hoi@sun-asterisk.com
 */
@Entity(tableName = "favorite")
data class Favorite(
    @PrimaryKey
    @ColumnInfo(name = "id_movie")
    val idMovie: Int
)