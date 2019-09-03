package com.sun.moviedb.data.repository

import androidx.lifecycle.LiveData
import com.sun.moviedb.base.BaseResponse
import com.sun.moviedb.base.ContextProviders
import com.sun.moviedb.base.NetworkBoundResource
import com.sun.moviedb.data.DatabaseManager
import com.sun.moviedb.data.dao.GenresDao
import com.sun.moviedb.data.dto.GenresDto
import com.sun.moviedb.data.entity.Genres
import com.sun.moviedb.data.remote.RemoteDataSource

/**
 * Created by nguyenxuanhoi on 2019-08-14.
 * @author nguyen.xuan.hoi@sun-asterisk.com
 */
class HomeRepository(
    val coroutines: ContextProviders,
    private val remoteDataSource: RemoteDataSource,
    private val genresDao:GenresDao
) : HomeContract {
    override fun getAllGenres(): LiveData<BaseResponse<List<Genres>>> {

        return object : NetworkBoundResource<List<Genres>, GenresDto>(coroutines) {
            override suspend fun saveCallResult(item: GenresDto) {
                val genres = item.list
                genresDao.save(genres)
            }

            override fun createCall(): LiveData<BaseResponse<GenresDto>> = remoteDataSource.getGenres()

            override fun shouldFetch(data: List<Genres>?): Boolean = true

            override suspend fun loadFromDatabase(): LiveData<List<Genres>> = genresDao.getAllGenres()

        }.asLiveData()

    }
}
