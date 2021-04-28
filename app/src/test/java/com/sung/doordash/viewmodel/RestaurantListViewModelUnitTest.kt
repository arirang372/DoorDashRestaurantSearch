package com.sung.doordash.viewmodel

import androidx.paging.Pager
import androidx.paging.PagingData
import com.sung.doordash.data.DoorDashRepository
import com.sung.doordash.data.KNOWN_LATITUDE
import com.sung.doordash.data.KNOWN_LONGETUDE
import com.sung.doordash.data.remote.DoorDashService
import com.sung.doordash.model.Restaurant
import kotlinx.coroutines.flow.Flow
import org.junit.Assert.assertTrue
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.powermock.api.mockito.PowerMockito
import org.powermock.core.classloader.annotations.PrepareForTest
import org.powermock.modules.junit4.PowerMockRunner

@PrepareForTest(Pager::class)
@RunWith(PowerMockRunner::class)
class RestaurantListViewModelUnitTest {
    @Mock
    private lateinit var flow: Flow<PagingData<Restaurant>>

    @Mock
    private lateinit var pager: Pager<Int, Restaurant>
    private lateinit var repository: DoorDashRepository

    @Mock
    private lateinit var service: DoorDashService
    private lateinit var testObject: RestaurantListViewModel

    @Test
    fun testRestaurantListViewModel() {
        PowerMockito.mockStatic(Pager::class.java)
        Mockito.`when`(pager.flow).thenReturn(flow)
        repository = object : DoorDashRepository(service) {
            override fun createPager(latitude: Double, longitude: Double) = pager
        }
        testObject = RestaurantListViewModel(repository)
        val targetFlow = testObject.getRestaurants(KNOWN_LATITUDE, KNOWN_LONGETUDE)
        assertTrue(targetFlow is Flow<PagingData<Restaurant>>)
    }
}