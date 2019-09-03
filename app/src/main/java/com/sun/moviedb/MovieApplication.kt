package com.sun.moviedb

import android.app.Application
import android.content.Context
import android.graphics.Color
import androidx.core.provider.FontRequest
import androidx.emoji.text.EmojiCompat
import androidx.emoji.text.FontRequestEmojiCompatConfig
import com.sun.moviedb.di.remoteModule
import com.sun.moviedb.di.sourceModule
import com.sun.moviedb.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

/**
 * Created by nguyenxuanhoi on 2019-08-05.
 * @author nguyen.xuan.hoi@sun-asterisk.com
 */
class MovieApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        INSTANCE = this
        startKoin {
            androidLogger()
            androidContext(this@MovieApplication)
            modules(listOf(remoteModule, sourceModule, viewModelModule))
        }
        val fontRequest = FontRequest(
            "com.google.android.gms.fonts",
            "com.google.android.gms",
            "Noto Color Emoji Compat",R.array.com_google_android_gms_fonts_certs)
        val config = FontRequestEmojiCompatConfig(this, fontRequest)
        EmojiCompat.init(config)
    }

    companion object {
        lateinit var INSTANCE: MovieApplication
            private set
        val applicationContext: Context?
            get() = if (::INSTANCE.isInitialized) INSTANCE.applicationContext else null
    }
}
