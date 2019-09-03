package com.sun.moviedb.data.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.RoomWarnings

import com.sun.moviedb.base.BaseDao
import com.sun.moviedb.data.entity.Genres
import com.sun.moviedb.data.entity.Movie
import com.sun.moviedb.data.entity.MovieGenresJoin

/**
 * Created by nguyenxuanhoi on 2019-08-14.
 * @author nguyen.xuan.hoi@sun-asterisk.com
 */
@Dao
interface MovieGenresJoinDao : BaseDao<MovieGenresJoin> {
    @Query(
        """SELECT * FROM movies INNER JOIN movies_genres_join ON
            movies.id=movies_genres_join.id_movie WHERE
            movies_genres_join.id_genres=:idGenres"""
    )
    @SuppressWarnings(RoomWarnings.CURSOR_MISMATCH)
    fun getMovieForGenres(idGenres: Int): List<Movie>

    @Query(
        """SELECT * FROM genres INNER JOIN movies_genres_join ON
            genres.id=movies_genres_join.id_genres WHERE
            movies_genres_join.id_movie=:idMovie"""
    )
    @SuppressWarnings(RoomWarnings.CURSOR_MISMATCH)
    fun getRepositoriesForUsers(idMovie: Int): List<Genres>

}
