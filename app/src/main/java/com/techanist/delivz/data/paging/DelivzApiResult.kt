package com.techanist.delivz.data.paging

import android.arch.lifecycle.LiveData
import android.arch.paging.PagedList
import com.techanist.delivz.data.dto.Delivery

data class DelivzApiResult(
        val data: LiveData<PagedList<Delivery>>,
        val networkStates: LiveData<NetworkState>)