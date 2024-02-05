package com.corylab.karmacode.ui.application

import android.app.Application
import android.content.Context

class KarmaCode: Application() {
    override fun onCreate() {
        super.onCreate()
        context = applicationContext
    }

    companion object {
        lateinit var context: Context
        private set
    }
}