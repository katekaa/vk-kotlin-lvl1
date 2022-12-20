package com.example.homework2.model

import androidx.paging.*
import com.example.homework2.model.database.BeerDao
import com.example.homework2.model.database.BeerRoomEntity
import com.example.homework2.model.network.ApiService
import com.example.homework2.model.repository.BeerRepository
import kotlinx.coroutines.flow.Flow

class DefaultBeerRepository (private val beerDao: BeerDao, private val beerApi: ApiService): BeerRepository {

    @OptIn(ExperimentalPagingApi::class)
    override fun getPagedBeers(): Flow<PagingData<BeerRoomEntity>> {

        return Pager(
            config = PagingConfig(pageSize = PAGE_SIZE, enablePlaceholders = false),
            remoteMediator = BeerRemoteMediator(beerDao, beerApi),
            pagingSourceFactory = {beerDao.getPagingSource()}
        ).flow
    }

    companion object {
        const val PAGE_SIZE = 20
    }

}