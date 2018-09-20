package com.techanist.delivz.data.paging

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.paging.PagedList
import android.util.Log
import com.techanist.delivz.data.ApiService
import com.techanist.delivz.data.db.DelivzLocalCache
import com.techanist.delivz.data.dto.Delivery
import io.reactivex.schedulers.Schedulers

class DelivzBoundaryCallback(
        private val service: ApiService,
        private val cache: DelivzLocalCache) : PagedList.BoundaryCallback<Delivery>() {

    companion object {
        private const val NETWORK_PAGE_SIZE = 20
        const val DATABASE_PAGE_SIZE = 20
    }

    init {
    }

    private var offset = 1

    private val _networkState = MutableLiveData<NetworkState>()
    val networkState: LiveData<NetworkState>
        get() = _networkState
    private var isRequestInProgress = false

    override fun onZeroItemsLoaded() {
        offset = 1
        requestAndSaveData()
    }

    override fun onItemAtEndLoaded(itemAtEnd: Delivery) {
        requestAndSaveData()
    }

    val retry = {
        requestAndSaveData()
    }

    private fun requestAndSaveData() {
        if (isRequestInProgress) return
        isRequestInProgress = true
        _networkState.postValue(NetworkState.LOADING)
        service.getDeliveries(offset, NETWORK_PAGE_SIZE)
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .subscribe({
                    _networkState.postValue(NetworkState.LOADED)
                    if (offset == 1) cache.deleteAll()
                    cache.insert(it) {
                        offset += NETWORK_PAGE_SIZE
                        isRequestInProgress = false
                    }
                }, {
                    isRequestInProgress = false
                    _networkState.postValue(NetworkState.FAILED(it.message))
                })
    }
}