package com.sun.moviedb.data.repository

import com.sun.moviedb.data.dto.ActorDTO
import com.sun.moviedb.data.dto.MovieDto
import com.sun.moviedb.data.remote.RemoteDataSource
import io.reactivex.Single

/**
 * Created by nguyenxuanhoi on 2019-08-29.
 * @author nguyen.xuan.hoi@sun-asterisk.com
 */
class ActorReponsitory (private val remoteDataSource: RemoteDataSource){
    fun getInfoActor(idActor:Int)= Single.create<ActorDTO> {emitter ->
        remoteDataSource.getProfile(idActor).subscribe({
            emitter.onSuccess(it)
        },{
            emitter.onError(it)
        })

    }
    fun getMovieByActor(idActor:Int,page:Int)= Single.create<MovieDto> {emitter ->
        remoteDataSource.getMoviesByActor(idActor,page).subscribe({
            emitter.onSuccess(it)
        },{
            emitter.onError(it)
        })

    }
}