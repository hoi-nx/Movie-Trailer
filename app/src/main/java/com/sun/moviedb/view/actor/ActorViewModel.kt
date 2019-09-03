package com.sun.moviedb.view.actor

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.sun.moviedb.data.dto.ActorDTO
import com.sun.moviedb.data.dto.DetailMovieDTO
import com.sun.moviedb.data.entity.Movie
import com.sun.moviedb.data.repository.ActorReponsitory
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Created by nguyenxuanhoi on 2019-08-29.
 * @author nguyen.xuan.hoi@sun-asterisk.com
 */
class ActorViewModel(
    application: Application,
    private val reponsitory: ActorReponsitory
) : AndroidViewModel(application) {
    private val idActor = MutableLiveData<Int>()
    val actor = MutableLiveData<ActorDTO>()
    val error = MutableLiveData<Throwable>()
    val movieByActor=MutableLiveData<List<Movie>>()

    fun initData(idActor: Int) {
        this.idActor.value = idActor
    }
    fun getProfile() {
        idActor.value?.let {
            reponsitory.getInfoActor(it).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    actor.value = it
                }, {
                    error.value = it
                })
        }
    }
    fun getMovieByCaster(){
        idActor.value?.let {
            reponsitory.getMovieByActor(it,1).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    movieByActor.value=it.result
                }, {
                    error.value = it
                })
        }
    }

}