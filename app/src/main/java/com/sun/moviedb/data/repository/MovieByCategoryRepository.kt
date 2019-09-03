package com.sun.moviedb.data.repository

import androidx.lifecycle.LiveData
import com.sun.moviedb.base.BaseResponse
import com.sun.moviedb.base.ContextProviders
import com.sun.moviedb.base.NetworkBoundResource
import com.sun.moviedb.data.dao.MovieDao
import com.sun.moviedb.data.dao.MovieGenresJoinDao
import com.sun.moviedb.data.dto.CategoryDTO
import com.sun.moviedb.data.dto.MovieDto
import com.sun.moviedb.data.entity.Movie
import com.sun.moviedb.data.entity.MovieGenresJoin
import com.sun.moviedb.data.remote.RemoteDataSource
import com.sun.moviedb.utils.CategoryQuery
import io.reactivex.Single

/**
 * Created by nguyenxuanhoi on 2019-08-20.
 * @author nguyen.xuan.hoi@sun-asterisk.com
 */
class MovieByCategoryRepository(
        val coroutines: ContextProviders,
        private val remoteDataSource: RemoteDataSource,
        private val movieDao: MovieDao
) : MovieDataSource {


    override fun getQueryTypeCategory() = listOf(
            CategoryDTO(CategoryQuery.POPULAR),
            CategoryDTO(CategoryQuery.UP_COMING)
    )

    override fun getMoviesByCategory(queryType: String, page: Int): LiveData<BaseResponse<List<Movie>>> =
            object : NetworkBoundResource<List<Movie>, MovieDto>(coroutines) {
                override suspend fun saveCallResult(item: MovieDto) {
                    val movies = item.result
                    movies.forEach {
                        it.queryType = queryType
                    }
                    movieDao.save(movies)
                }

                override fun createCall(): LiveData<BaseResponse<MovieDto>> =
                        remoteDataSource.getMoviesByCategory(queryType, page)

                override fun shouldFetch(data: List<Movie>?): Boolean = true

                override suspend fun loadFromDatabase(): LiveData<List<Movie>> =
                        movieDao.findAllMovieByCategory(queryType)

            }.asLiveData()



}
