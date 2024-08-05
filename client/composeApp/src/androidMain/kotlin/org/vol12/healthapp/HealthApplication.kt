package org.vol12.healthapp

import android.app.Application
import di.initKoin
import org.koin.android.ext.koin.androidContext

class HealthApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        initKoin {
            androidContext(this@HealthApplication)
        }
    }
}
