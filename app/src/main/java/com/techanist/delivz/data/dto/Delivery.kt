package com.techanist.delivz.data.dto

import android.arch.persistence.room.Embedded
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import java.io.Serializable

@Entity
data class Delivery(@PrimaryKey val id: Int, val description: String,
                    val imageUrl: String, @Embedded val location: Location) : Serializable {

    fun equals(other: Delivery): Boolean {
        return id == other.id &&
                description.contentEquals(other.description) &&
                imageUrl.contentEquals(other.imageUrl) &&
                location.equals(location)
    }

}