package com.example.homework2.model.repository

import androidx.paging.PagingData
import com.example.homework2.model.database.BeerRoomEntity
import kotlinx.coroutines.flow.Flow

interface BeerRepository {

    fun getPagedBeers(): Flow<PagingData<BeerRoomEntity>>

}