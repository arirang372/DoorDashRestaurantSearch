package com.sung.doordash.data.remote

import androidx.paging.PagingSource
import com.sung.doordash.model.Restaurant
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

private const val STARTING_PAGE_INDEX = 1

class DoorDashPagingSource(
    private val service: DoorDashService,
    private val latitude: Double,
    private val longitude: Double
) : PagingSource<Int, Restaurant>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Restaurant> {
        val page = params.key ?: STARTING_PAGE_INDEX
        return try {
            val response = service.getRestaurants(
                latitude = latitude,
                longitude = longitude,
                page,
                params.loadSize
            )
            response.stores.forEach {
                CoroutineScope(Dispatchers.IO).launch {
                    it.status.isStoreOpen =
                        withContext(Dispatchers.Default) {
                            service.getRestaurantDetails(
                                it.id
                            ).menus
                        }[0].statusType == "open"
                }
            }
            LoadResult.Page(
                data = response.stores,
                prevKey = if (page == STARTING_PAGE_INDEX) null else page - 1,
                nextKey = if (page == response.nextOffSet) null else page + 1
            )
        } catch (exception: Exception) {
            LoadResult.Error(exception)
        }
    }
}