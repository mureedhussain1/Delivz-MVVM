package com.techanist.delivz.di

import com.techanist.delivz.DelivzApp
import com.techanist.delivz.viewmodel.DeliveryViewModel
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, NetworkModule::class, DatabaseModule::class])
interface AppComponent {
    fun inject(viewModel: DeliveryViewModel)
    fun inject(viewModel: DelivzApp)
}