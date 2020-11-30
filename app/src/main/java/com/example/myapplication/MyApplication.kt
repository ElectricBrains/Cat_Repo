package com.example.myapplication

import android.app.Application
import android.content.Intent
import android.content.IntentFilter
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MyApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        filesDir
        cacheDir
    }

}