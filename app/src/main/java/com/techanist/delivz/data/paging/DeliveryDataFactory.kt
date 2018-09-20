package com.techanist.delivz.data.paging

import android.arch.paging.DataSource
import com.techanist.delivz.data.dto.Delivery
import android.arch.lifecycle.MutableLiveData
import com.techanist.delivz.data.ApiService
import javax.inject.Inject


class DeliveryDataFactory @Inject constructor(apiService: ApiService) : DataSource.Factory<Int, Delivery>() {

    var mutableLiveData: MutableLiveData<DeliveryDatasource>
    private lateinit var deliveryDatasource: DeliveryDatasource

    init {
        mutableLiveData = MutableLiveData()
    }


    override fun create(): DataSource<Int, Delivery> {
//        deliveryDatasource = DeliveryDatasource()
//        mutableLiveData.postValue(deliveryDatasource)
        return deliveryDatasource
    }
}