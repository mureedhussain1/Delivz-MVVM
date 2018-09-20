package com.techanist.delivz.di

import com.techanist.delivz.DelivzApp
import dagger.Module
import dagger.Provides
import dagger.Reusable

@Module
class AppModule(val app: DelivzApp) {

    @Provides
    @Reusable
    internal fun provideApp(): DelivzApp {
        return app
    }

}