package com.my.otpsessiontracking

import android.app.Application
import timber.log.Timber

class MyApp : Application(){

    //session/auth tracking must be done on application level, not inside activities
    override fun onCreate() {
        super.onCreate()

        Timber.plant(Timber.DebugTree())

        Timber.d("App Started Successfully")
    }
}