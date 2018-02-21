package com.example.com.mptvshow

import android.app.Application
import com.example.com.mptvshow.di.AppModule
import com.example.com.mptvshow.di.ApplicationComponent
import com.example.com.mptvshow.di.DaggerApplicationComponent
import com.facebook.stetho.Stetho
import uk.co.chrisjenx.calligraphy.CalligraphyConfig

class MPTvShowApplication: Application() {


    val instance : MPTvShowApplication = this

    companion object {
        lateinit var mApplicationComponent: ApplicationComponent
    }

    override fun onCreate() {
        super.onCreate()

        mApplicationComponent = DaggerApplicationComponent.builder()
                .appModule(AppModule(this))
                .build()

        mApplicationComponent.inject(this)


        if(BuildConfig.DEBUG) {
            Stetho.initializeWithDefaults(this)

            Stetho.initialize(
                    Stetho.newInitializerBuilder(this)
                            .enableDumpapp(Stetho.defaultDumperPluginsProvider(this))
                            .build())
        }
    }

    fun get(): MPTvShowApplication {
        return instance
    }

    fun getCoreComponent(): ApplicationComponent {
        return mApplicationComponent
    }

}