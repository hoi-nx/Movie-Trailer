package com.sun.moviedb.di

import com.sun.moviedb.MovieApplication
import com.sun.moviedb.view.actor.ActorViewModel
import com.sun.moviedb.view.category.MovieByCategoryViewModel
import com.sun.moviedb.view.detail.DetailMovieViewModel
import com.sun.moviedb.view.favorite.FavoriteViewModel
import com.sun.moviedb.view.genres.MovieByGenresViewModel
import com.sun.moviedb.view.home.HomeViewModel
import com.sun.moviedb.view.search.SearchViewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

/**
 * Created by nguyenxuanhoi on 2019-08-07.
 * @author nguyen.xuan.hoi@sun-asterisk.com
 */
val viewModelModule = module {
    single(named(MovieApplication::class.java.name)) { androidApplication() }
    viewModel { HomeViewModel(get(named(MovieApplication::class.java.name)), get(), get()) }
    viewModel { MovieByCategoryViewModel(get(named(MovieApplication::class.java.name)), get()) }
    viewModel { MovieByGenresViewModel(get(named(MovieApplication::class.java.name)), get()) }
    viewModel { DetailMovieViewModel(get(named(MovieApplication::class.java.name)), get()) }
    viewModel { SearchViewModel(get(named(MovieApplication::class.java.name)), get(named(RemoteProperties.RX_ANDOIRD))) }
    viewModel { FavoriteViewModel(get(named(MovieApplication::class.java.name)), get()) }
    viewModel { ActorViewModel (get(named(MovieApplication::class.java.name)), get()) }

}
