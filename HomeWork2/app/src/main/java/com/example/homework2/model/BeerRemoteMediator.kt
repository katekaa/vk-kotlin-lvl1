package com.example.homework2.model

import android.util.Log
import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import com.example.homework2.model.database.BeerDao
import com.example.homework2.model.database.BeerRoomEntity
import com.example.homework2.model.network.ApiService

@OptIn(ExperimentalPagingApi::class)
class BeerRemoteMediator (
    private val beerDao: BeerDao,
    private val beerApi: ApiService):
    RemoteMediator<Int, BeerRoomEntity>() {
    private var pageIndex = 0
    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, BeerRoomEntity>
    ): MediatorResult {

        pageIndex = getPageIndex(loadType, state) ?: return MediatorResult.Success(endOfPaginationReached = true)
        return try {
//            Log.d("doogo", "api with page ${pageIndex} and limit ${state.config.pageSize}")
            val beers = beerApi.getBeersList(pageIndex, state.config.pageSize).map { it.toBeerRoomEntity() }
//            Log.d("doggo", "size ${beers.size} type ${loadType}")
            if (loadType == LoadType.REFRESH) {
                beerDao.refresh(beers)
            } else {
                beerDao.insertAll(beers)
            }
            MediatorResult.Success(endOfPaginationReached = beers.size < state.config.pageSize)
        } catch (e: Exception) {
//            Log.d("doggo", "hi it's an error")
            MediatorResult.Error(e)
        }
    }

    private fun getPageIndex(loadType: LoadType, state: PagingState<Int, BeerRoomEntity>): Int? {
        pageIndex = when (loadType) {
            LoadType.REFRESH -> 1
            LoadType.APPEND -> {
                val lastItem = state.lastItemOrNull() ?: return null

                ++pageIndex
            }
            LoadType.PREPEND -> return null
        }
        return pageIndex
    }

}