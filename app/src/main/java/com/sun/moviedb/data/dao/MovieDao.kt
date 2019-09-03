package com.sun.moviedb.data.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query

import com.sun.moviedb.base.BaseDao
import com.sun.moviedb.data.entity.Movie

/**
 * Created by nguyenxuanhoi on 2019-08-14.
 * @author nguyen.xuan.hoi@sun-asterisk.com
 */
@Dao
interface MovieDao : BaseDao<Movie> {

    @Query("SELECT * FROM movies")
    fun getAllMovie(): LiveData<List<Movie>>

    @Query("SELECT * FROM movies WHERE query_type=:queryType")
    fun findAllMovieByCategory(queryType: String): LiveData<List<Movie>>
}
