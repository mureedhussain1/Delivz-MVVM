package com.techanist.delivz

import android.app.Application
import com.techanist.delivz.di.*

class DelivzApp : Application() {


    lateinit var component: AppComponent

    companion object {
        lateinit var app: DelivzApp
    }

    override fun onCreate() {
        super.onCreate()
        app = this
        initNetworkComponent()
        component.inject(this)
    }

    private fun initNetworkComponent(){
        component = DaggerAppComponent.builder()
                .appModule(AppModule(app))
                .networkModule(NetworkModule())
                .databaseModule(DatabaseModule())
                .build();
    }
}