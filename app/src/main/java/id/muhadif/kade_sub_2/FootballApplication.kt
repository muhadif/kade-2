package id.muhadif.kade_sub_2

import android.app.Application
import id.muhadif.kade_sub_2.di.appModule
import org.koin.android.ext.android.startKoin

class FootballApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin(this, listOf(appModule))
    }

}