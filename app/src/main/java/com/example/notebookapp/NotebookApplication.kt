package com.example.notebookapp

import android.app.Application
import androidx.appcompat.app.AppCompatDelegate
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class NotebookApplication:Application() {

    companion object {
        lateinit var instance: NotebookApplication
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        //disable night mode
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
    }

}