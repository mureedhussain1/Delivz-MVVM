package com.techanist.delivz.data.db

import android.arch.persistence.room.RoomDatabase
import android.arch.persistence.room.Database
import com.techanist.delivz.data.dto.Delivery
import android.arch.persistence.room.Room
import android.content.Context


@Database(entities = [Delivery::class], version = 1)
abstract class DelivzDatabase : RoomDatabase() {

    companion object {

        private var INSTANCE: DelivzDatabase? = null

        fun getDatabase(context: Context): DelivzDatabase? {
            if (INSTANCE == null) {
                synchronized(DelivzDatabase::class.java) {
                    if (INSTANCE == null) {
                        INSTANCE = Room.databaseBuilder(context.applicationContext,
                                DelivzDatabase::class.java, "delivz_database")
                                .build()
                    }
                }
            }
            return INSTANCE
        }
    }

    abstract fun delivzDao(): DelivzDao

}