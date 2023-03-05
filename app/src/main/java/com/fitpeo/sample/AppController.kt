package com.fitpeo.sample

import android.app.Application
import android.content.Context
import com.fitpeo.sample.utils.ApplicationSettings
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

@HiltAndroidApp
class AppController : Application() {
    companion object {
        lateinit var mInstance: AppController
    }

    private var isFirstTimeLoaded = false


    lateinit var mContext: Context

    override fun onCreate() {
        super.onCreate()
        mContext = this
        mInstance = this

        ApplicationSettings.init(mContext)
        isFirstTimeLoaded = true
        Timber.plant(Timber.DebugTree())

    }

    fun isFirstTimeLoaded(): Boolean {
        return isFirstTimeLoaded
    }

    fun setFirstTimeLoaded(firstTimeLoaded: Boolean) {
        isFirstTimeLoaded = firstTimeLoaded
    }

    @Synchronized
    fun getInstance(): AppController {
        return mInstance
    }
}