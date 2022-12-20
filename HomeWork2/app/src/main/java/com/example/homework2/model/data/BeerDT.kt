package com.example.homework2.model.data

import com.example.homework2.model.database.BeerRoomEntity
import com.google.gson.annotations.SerializedName

data class BeerDT(
    val id: Int,
    @SerializedName ("image_url") val image: String
) {
    fun toBeerRoomEntity(): BeerRoomEntity {
        return BeerRoomEntity(
            id = id,
            image = image
        )
    }
}

