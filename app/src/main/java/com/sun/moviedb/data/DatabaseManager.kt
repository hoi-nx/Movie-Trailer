package com.sun.moviedb.data

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.sun.moviedb.BuildConfig
import com.sun.moviedb.MovieApplication
import com.sun.moviedb.data.dao.FavoriteDAO
import com.sun.moviedb.data.dao.GenresDao
import com.sun.moviedb.data.dao.MovieDao
import com.sun.moviedb.data.dao.MovieGenresJoinDao
import com.sun.moviedb.data.entity.Favorite
import com.sun.moviedb.data.entity.Genres
import com.sun.moviedb.data.entity.Movie
import com.sun.moviedb.data.entity.MovieGenresJoin

/**
 * Created by nguyenxuanhoi on 2019-08-05.
 * @author nguyen.xuan.hoi@sun-asterisk.com
 */
object DatabaseManager {
    private const val APP_DATABASE_NAME = "moviedb.db"
    val appDatabase by lazy {
        Room.databaseBuilder(MovieApplication.applicationContext!!, AppDatabase::class.java, APP_DATABASE_NAME)
                .allowMainThreadQueries()
                .fallbackToDestructiveMigration()
                .build()
    }

    @Database(entities = [Genres::class, Movie::class,Favorite::class],
            version = BuildConfig.APP_DATABASE_VERSION, exportSchema = false)
    abstract class AppDatabase : RoomDatabase() {
        abstract fun genresDao(): GenresDao
        abstract fun movieDao(): MovieDao
        abstract fun favoriteDao(): FavoriteDAO
    }
}
