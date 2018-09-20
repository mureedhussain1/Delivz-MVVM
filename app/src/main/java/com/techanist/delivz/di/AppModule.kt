package com.techanist.delivz.di

import com.techanist.delivz.DelivzApp
import dagger.Module
import dagger.Provides
import dagger.Reusable

@Module
class AppModule(delivzApp: DelivzApp) {

    private var app: DelivzApp

    init {
        app = delivzApp
    }

    @Provides
    @Reusable
    internal fun provideApp(): DelivzApp {
        return app
    }

}