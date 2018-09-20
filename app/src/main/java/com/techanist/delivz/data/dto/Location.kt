package com.techanist.delivz.data.dto

import android.arch.persistence.room.Entity
import java.io.Serializable

@Entity
data class Location(val lat: Double, val lng: Double, val address: String) : Serializable {

    fun equals(other: Location): Boolean {
        return lat == other.lat &&
                lng == other.lng &&
                address.contentEquals(other.address)
    }

}