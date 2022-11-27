package com.example.homework2.model.database

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import kotlinx.coroutines.flow.Flow

@Dao
interface BeerDao {
    @Query("SELECT * FROM images ORDER BY image_id")
    fun getPagingSource(): PagingSource<Int, BeerRoomEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(beers: List<BeerRoomEntity>)

    @Query("DELETE FROM images")
    suspend fun clearAll()

    @Transaction
    suspend fun refresh(beers: List<BeerRoomEntity>) {
        clearAll()
        insertAll(beers)
    }
}