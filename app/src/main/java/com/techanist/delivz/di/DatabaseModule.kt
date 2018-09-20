package com.techanist.delivz.di

import com.techanist.delivz.DelivzApp
import com.techanist.delivz.data.db.DelivzDao
import com.techanist.delivz.data.db.DelivzDatabase
import com.techanist.delivz.data.db.DelivzLocalCache
import dagger.Module
import dagger.Provides
import dagger.Reusable
import java.util.concurrent.Executor
import java.util.concurrent.Executors

@Module
class DatabaseModule {

    @Provides
    @Reusable
    internal fun provideDelivzDatabase(app: DelivzApp): DelivzDatabase {
        return DelivzDatabase.getDatabase(app)!!
    }

    @Provides
    @Reusable
    internal fun provideDelivzDao(database: DelivzDatabase): DelivzDao {
        return database.delivzDao()
    }

    @Provides
    @Reusable
    internal fun provideDelivzLocalCache(delivzDao: DelivzDao, executor: Executor): DelivzLocalCache {
        return DelivzLocalCache(delivzDao, executor)
    }


    @Provides
    @Reusable
    internal fun provideExecutor(): Executor {
        return Executors.newSingleThreadExecutor()
    }

}