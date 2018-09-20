package com.techanist.delivz.data

import android.arch.paging.LivePagedListBuilder
import android.util.Log
import com.techanist.delivz.data.db.DelivzLocalCache
import com.techanist.delivz.data.paging.DelivzApiResult
import com.techanist.delivz.data.paging.DelivzBoundaryCallback


class ApiRepository(
        private val service: ApiService,
        private val cache: DelivzLocalCache) {

    fun getDeliveries(): DelivzApiResult {
        Log.d("paging_delivz", "ApiRepo.getDeliveries")
        val dataSourceFactory = cache.getAll()
        val boundaryCallback = DelivzBoundaryCallback(service, cache)
        val networkStates = boundaryCallback.networkState
        val data = LivePagedListBuilder(dataSourceFactory, DelivzBoundaryCallback.DATABASE_PAGE_SIZE)
                .setBoundaryCallback(boundaryCallback)
                .build()
        boundaryCallback.onZeroItemsLoaded()
        return DelivzApiResult(data, networkStates)
    }
}
