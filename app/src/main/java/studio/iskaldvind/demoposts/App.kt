package studio.iskaldvind.demoposts

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import studio.iskaldvind.demoposts.di.application
import studio.iskaldvind.demoposts.di.main

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(applicationContext)
            modules(listOf(application, main))
        }
    }
}