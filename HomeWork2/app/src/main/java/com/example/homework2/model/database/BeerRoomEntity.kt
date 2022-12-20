package com.example.homework2.model.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.homework2.view.Beer

@Entity(tableName = "images")
data class BeerRoomEntity(
    @PrimaryKey
    @ColumnInfo(name = "image_id")
    val id: Int,
    @ColumnInfo(name = "image_url") val image: String
) {
    fun toBeer(): Beer {
        return Beer(id=id, image=image)
    }
}
