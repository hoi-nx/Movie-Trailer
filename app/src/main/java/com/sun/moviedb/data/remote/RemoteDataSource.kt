package com.sun.moviedb.data.remote

import androidx.lifecycle.LiveData
import com.sun.moviedb.base.BaseResponse
import com.sun.moviedb.data.dto.ActorDTO
import com.sun.moviedb.data.dto.DetailMovieDTO
import com.sun.moviedb.data.dto.GenresDto
import com.sun.moviedb.data.dto.MovieDto
import com.sun.moviedb.data.entity.Movie
import io.reactivex.Observable
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RemoteDataSource {
    @GET("genre/movie/list")
    fun getGenres(): LiveData<BaseResponse<GenresDto>>

    @GET("movie/{type}")
    fun getMoviesByCategory(@Path("type") type: String, @Query("page") page: Int): LiveData<BaseResponse<MovieDto>>

    @GET("discover/movie")
     fun getMoviesByGenre(
        @Query("with_genres") idGenre: Int,
        @Query("page") page: Int
    ): Single<MovieDto>
    @GET("movie/{movie_id}?append_to_response=credits,videos")
     fun getMovieDetail(@Path("movie_id") id: Int): Single<DetailMovieDTO>
    @GET("search/movie")
     fun searchMovieByName(
        @Query("query") key: String,
        @Query("page") page: Int
    ): Single<MovieDto>
    @GET("person/{actor_id}")
     fun getProfile(@Path("actor_id") actorId: Int): Single<ActorDTO>

    @GET("discover/movie")
     fun getMoviesByActor(
        @Query("with_cast") idCast: Int,
        @Query("page") page: Int
    ): Single<MovieDto>
}
