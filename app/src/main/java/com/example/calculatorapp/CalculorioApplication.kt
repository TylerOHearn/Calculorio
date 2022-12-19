package com.example.calculatorapp

import android.app.Application
import timber.log.Timber

class CalculorioApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}