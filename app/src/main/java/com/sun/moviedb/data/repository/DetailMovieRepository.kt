package com.sun.moviedb.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import com.sun.moviedb.base.BaseResponse
import com.sun.moviedb.data.dao.FavoriteDAO
import com.sun.moviedb.data.dto.DetailMovieDTO
import com.sun.moviedb.data.entity.Favorite
import com.sun.moviedb.data.entity.Movie
import com.sun.moviedb.data.remote.RemoteDataSource
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

/**
 * Created by nguyenxuanhoi on 2019-08-25.
 * @author nguyen.xuan.hoi@sun-asterisk.com
 */
class DetailMovieRepository(private val remoteDataSource: RemoteDataSource,
                            private val favoriteDAO: FavoriteDAO) {

    fun getDetailMovie(idMovie: Int): Single<DetailMovieDTO> = Single.create { emitter ->
        remoteDataSource.getMovieDetail(idMovie).subscribe({
            emitter.onSuccess(it)
        }, {
            emitter.onError(it)
        })

    }
    fun toggleFavoriteMovie(idMovie: Int, isFavorite: Boolean) {
        GlobalScope.launch(Dispatchers.IO) {
            if (isFavorite) {
                favoriteDAO.delete(Favorite(idMovie=idMovie))
            } else {
                favoriteDAO.save(Favorite(idMovie=idMovie))
            }
        }
    }
    fun getFavoriteMovies(): LiveData<BaseResponse<List<Movie>>> {
        val data = MediatorLiveData<BaseResponse<List<Movie>>>()
        data.value = BaseResponse.loading(null)
        data.addSource(favoriteDAO.getFavoriteMovies()) {
            it?.let {
                data.value = BaseResponse.success(it)
            }
        }
        return data
    }
    fun isFavoriteMovie(movieId: Int): LiveData<Boolean> {
        val isFavorite = MediatorLiveData<Boolean>()
        val favCount = favoriteDAO.isFavorite(movieId)
        isFavorite.addSource(favCount) { data ->
            data?.let {
                isFavorite.value = it.favCount > 0
            }
        }
        return isFavorite
    }
}