package com.sung.doordash.data

import androidx.annotation.VisibleForTesting
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.sung.doordash.data.remote.DoorDashPagingSource
import com.sung.doordash.data.remote.DoorDashService
import com.sung.doordash.model.Restaurant
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

open class DoorDashRepository @Inject constructor(private val service: DoorDashService) {
    fun getRestaurants(latitude: Double, longitude: Double): Flow<PagingData<Restaurant>> {
        return createPager(latitude, longitude).flow
    }

    @VisibleForTesting
    open fun createPager(latitude: Double, longitude: Double) =
        Pager(
            config = PagingConfig(enablePlaceholders = false, pageSize = DEFAULT_PAGE_SIZE),
            pagingSourceFactory = {
                DoorDashPagingSource(service, latitude, longitude)
            }
        )

    companion object {
        const val DEFAULT_PAGE_SIZE = 20
    }
}