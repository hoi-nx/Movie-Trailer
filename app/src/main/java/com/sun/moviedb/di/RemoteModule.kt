package com.sun.moviedb.di

import com.sun.moviedb.BuildConfig
import com.sun.moviedb.data.remote.RemoteDataSource
import com.sun.moviedb.data.remote.AuthInterceptor
import com.sun.moviedb.data.remote.NetworkInterceptor
import com.sun.moviedb.data.remote.ResponseInterceptor
import com.sun.moviedb.utils.LiveDataCallAdapterFactory
import io.reactivex.schedulers.Schedulers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RemoteProperties {
    const val TIME_OUT = 10L
    const val RX_ANDOIRD = "rx"
}

fun createBaseUrl(): String = BuildConfig.BASE_URL

inline fun <reified T> createWebService(
        baseUrl: String, gsonConverterFactory: GsonConverterFactory,
        liveDataCallAdapterFactory: LiveDataCallAdapterFactory,
        client: OkHttpClient
): T {
    val retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(gsonConverterFactory)
            .addCallAdapterFactory(liveDataCallAdapterFactory)
            .client(client)
            .build()
    return retrofit.create(T::class.java)
}

inline fun <reified T> createWebServiceWithRx(
        baseUrl: String, gsonConverterFactory: GsonConverterFactory,
        adapterFactory: RxJava2CallAdapterFactory,
        client: OkHttpClient
): T {
    val retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(gsonConverterFactory)
            .addCallAdapterFactory(adapterFactory)
            .client(client)
            .build()
    return retrofit.create(T::class.java)
}

private fun createOkHttpClient(): OkHttpClient {
    val builder = OkHttpClient.Builder()
            .addInterceptor(logInterceptor())
            .addInterceptor(NetworkInterceptor())
            .addInterceptor(AuthInterceptor())
            .addInterceptor(ResponseInterceptor())
            .connectTimeout(RemoteProperties.TIME_OUT, TimeUnit.SECONDS)
            .readTimeout(RemoteProperties.TIME_OUT, TimeUnit.SECONDS)
            .writeTimeout(RemoteProperties.TIME_OUT, TimeUnit.SECONDS)
    return builder.build()
}

private fun logInterceptor(): HttpLoggingInterceptor = HttpLoggingInterceptor().apply {
    level = if (BuildConfig.DEBUG) {
        HttpLoggingInterceptor.Level.BODY
    } else {
        HttpLoggingInterceptor.Level.NONE
    }
}

val remoteModule = module {
    single { createBaseUrl() }
    single { GsonConverterFactory.create() }
    single { LiveDataCallAdapterFactory() }
    single { createOkHttpClient() }
    single { RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()) }
    single(named(RemoteProperties::class.java.name)) {
        createWebService<RemoteDataSource>(get(), get(), get(), get())
    }
    single(named(RemoteProperties.RX_ANDOIRD)) { createWebServiceWithRx<RemoteDataSource>(get(), get(), get(), get()) }
}
