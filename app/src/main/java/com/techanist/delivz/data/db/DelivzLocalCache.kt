package com.techanist.delivz.data.db

import android.arch.paging.DataSource
import android.util.Log
import com.techanist.delivz.data.dto.Delivery
import java.util.concurrent.Executor

class DelivzLocalCache(
        private val delivzDao: DelivzDao,
        private val ioExecutor: Executor) {

    fun insert(delivz: List<Delivery>, insertFinished: () -> Unit) {
        ioExecutor.execute {
            delivzDao.save(delivz)
            insertFinished()
        }
    }

    fun getAll(): DataSource.Factory<Int, Delivery> {
        return delivzDao.loadAll()
    }

    fun deleteAll() {
        delivzDao.deleteAll()
    }

}