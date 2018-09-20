package com.techanist.delivz.data.paging

import android.arch.lifecycle.MutableLiveData
import android.arch.paging.PageKeyedDataSource
import com.techanist.delivz.data.ApiService
import com.techanist.delivz.data.dto.Delivery
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class DeliveryDatasource @Inject constructor(apiService: ApiService) : PageKeyedDataSource<Int, Delivery>() {

    var networkState: MutableLiveData<NetworkState>
    var initialLoading: MutableLiveData<NetworkState>
    private var apiService: ApiService

    init {
        networkState = MutableLiveData()
        initialLoading = MutableLiveData()
        this.apiService = apiService
    }


    override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int, Delivery>) {
        initialLoading.postValue(NetworkState.LOADING);
        networkState.postValue(NetworkState.LOADING);
        apiService.getDeliveries(1, params.requestedLoadSize)
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .subscribe({
                    networkState.postValue(NetworkState.LOADED)
                    initialLoading.postValue(NetworkState.LOADED)
                }, {
                    it.printStackTrace()
                    networkState.postValue(NetworkState.FAILED("Error! Failed to load data"))
                })
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Delivery>) {

    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Delivery>) {

    }


}