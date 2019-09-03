package com.sun.moviedb.data.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import com.sun.moviedb.base.BaseDao
import com.sun.moviedb.data.entity.Genres

/**
 * Created by nguyenxuanhoi on 2019-08-14.
 * @author nguyen.xuan.hoi@sun-asterisk.com
 */
@Dao
interface GenresDao : BaseDao<Genres> {

    @Query("SELECT * FROM genres")
    fun getAllGenres(): LiveData<List<Genres>>
}
