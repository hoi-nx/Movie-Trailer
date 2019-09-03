package com.sun.moviedb.data.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import com.sun.moviedb.base.BaseDao
import com.sun.moviedb.data.dto.FavoriteCount
import com.sun.moviedb.data.entity.Favorite
import com.sun.moviedb.data.entity.Movie

/**
 * Created by nguyenxuanhoi on 2019-08-26.
 * @author nguyen.xuan.hoi@sun-asterisk.com
 */
@Dao
interface FavoriteDAO : BaseDao<Favorite> {
    @Query("SELECT * FROM movies INNER JOIN favorite ON favorite.id_movie = id ORDER BY releaseDate DESC")
    fun getFavoriteMovies(): LiveData<List<Movie>>

//    @Query("SELECT * FROM favorite WHERE id=:idMovie")
//    fun findById(idMovie: Int): LiveData<Movie>

    @Query("SELECT COUNT(*) as favCount FROM favorite WHERE id_movie = :idMovie")
    fun isFavorite(idMovie: Int): LiveData<FavoriteCount>

}