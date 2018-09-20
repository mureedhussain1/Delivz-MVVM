package com.techanist.delivz.data.db

import android.arch.lifecycle.LiveData
import android.arch.paging.DataSource
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import com.techanist.delivz.data.dto.Delivery


@Dao
interface DelivzDao {
    @Insert/*(onConflict = REPLACE)*/
    fun save(deliveries: List<Delivery>)

    @Query("SELECT * FROM delivery WHERE id = :deliveryId")
    fun load(deliveryId: Int): LiveData<Delivery>

    @Query("SELECT * FROM delivery")
    fun loadAll(): DataSource.Factory<Int, Delivery>

    @Query("DELETE from delivery")
    fun deleteAll()
}