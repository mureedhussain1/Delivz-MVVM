package com.techanist.delivz.di

import com.techanist.delivz.data.ApiRepository
import com.techanist.delivz.data.ApiService
import com.techanist.delivz.data.Constants
import com.techanist.delivz.data.db.DelivzLocalCache
import dagger.Module
import dagger.Provides
import dagger.Reusable
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

@Module
class NetworkModule() {

    @Provides
    @Reusable
    internal fun provideApiService(retrofit: Retrofit): ApiService = retrofit.create(ApiService::class.java)

    @Provides
    @Reusable
    internal fun provideRetrofit(): Retrofit = Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()


    @Provides
    @Reusable
    internal fun provideApiRepository(apiService: ApiService,
                                      delivzLocalCache: DelivzLocalCache): ApiRepository = ApiRepository(apiService, delivzLocalCache)


}