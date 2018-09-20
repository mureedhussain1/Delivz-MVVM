package com.techanist.delivz.data

import android.arch.paging.LivePagedListBuilder
import com.techanist.delivz.data.db.DelivzLocalCache
import com.techanist.delivz.data.paging.DelivzApiResult
import com.techanist.delivz.data.paging.DelivzBoundaryCallback


class ApiRepository(
        private val service: ApiService,
        private val cache: DelivzLocalCache) {

    var retry = {}

    fun getDeliveries(): DelivzApiResult {
        val dataSourceFactory = cache.getAll()
        val boundaryCallback = DelivzBoundaryCallback(service, cache)
        retry = {
            boundaryCallback.retry()
        }
        val networkStates = boundaryCallback.networkState
        val data = LivePagedListBuilder(dataSourceFactory, DelivzBoundaryCallback.DATABASE_PAGE_SIZE)
                .setBoundaryCallback(boundaryCallback)
                .build()
        boundaryCallback.onZeroItemsLoaded()
        return DelivzApiResult(data, networkStates)
    }
}
