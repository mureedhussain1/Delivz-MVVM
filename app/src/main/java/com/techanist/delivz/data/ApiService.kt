package com.techanist.delivz.data

import com.techanist.delivz.data.dto.Delivery
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("/deliveries")
    fun getDeliveries(@Query("offset") offset: Int,
                      @Query("limit") limit: Int): Observable<List<Delivery>>

}