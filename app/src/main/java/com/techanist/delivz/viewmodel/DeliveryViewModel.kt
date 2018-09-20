package com.techanist.delivz.viewmodel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Transformations
import android.arch.lifecycle.ViewModel
import android.arch.paging.PagedList
import android.view.View
import com.techanist.delivz.DelivzApp
import com.techanist.delivz.data.ApiRepository
import com.techanist.delivz.data.dto.Delivery
import com.techanist.delivz.data.paging.DelivzApiResult
import com.techanist.delivz.data.paging.NetworkState
import io.reactivex.disposables.Disposable
import javax.inject.Inject

class DeliveryViewModel : ViewModel() {

    @Inject
    lateinit var apiRepository: ApiRepository

    private lateinit var subscription: Disposable

    init {
        DelivzApp.app.component.inject(this)
    }

    val result = MutableLiveData<DelivzApiResult>()

    val delivz: LiveData<PagedList<Delivery>> = Transformations.switchMap(result
    ) { it -> it.data }
    val networkStates: LiveData<NetworkState> = Transformations.switchMap(result
    ) { it -> it.networkStates }

    fun getDeliveries() {
        result.postValue(apiRepository.getDeliveries())
    }


    override fun onCleared() {
        super.onCleared()
//        subscription.dispose()
    }

}