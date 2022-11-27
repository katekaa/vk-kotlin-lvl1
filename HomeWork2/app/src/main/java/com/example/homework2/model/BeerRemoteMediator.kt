package com.example.homework2.model

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

        pageIndex = getPageIndex(loadType) ?: return MediatorResult.Success(endOfPaginationReached = true)
        return try {
            val beers = beerApi.getBeersList(pageIndex, state.config.pageSize).map { it.toBeerRoomEntity() }
            if (loadType == LoadType.REFRESH) {
                beerDao.refresh(beers)
            } else {
                beerDao.insertAll(beers)
            }
            MediatorResult.Success(endOfPaginationReached = beers.size < state.config.pageSize)
        } catch (e: Exception) {
            MediatorResult.Error(e)
        }
    }

    private fun getPageIndex(loadType: LoadType): Int? {
        pageIndex = when (loadType) {
            LoadType.REFRESH -> 1
            LoadType.APPEND -> ++pageIndex
            LoadType.PREPEND -> return null
        }
        return pageIndex
    }

}