package com.sung.doordash.data

import androidx.paging.Pager
import androidx.paging.PagingData
import com.sung.doordash.data.remote.DoorDashService
import com.sung.doordash.model.Restaurant
import kotlinx.coroutines.flow.Flow
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.powermock.api.mockito.PowerMockito.mockStatic
import org.powermock.core.classloader.annotations.PrepareForTest
import org.powermock.modules.junit4.PowerMockRunner

@PrepareForTest(Pager::class)
@RunWith(PowerMockRunner::class)
class DoorDashRepositoryTest {
    @Mock
    private lateinit var pager: Pager<Int, Restaurant>

    @Mock
    private lateinit var flow: Flow<PagingData<Restaurant>>

    @Mock
    private lateinit var service: DoorDashService

    private lateinit var testObject: DoorDashRepository

    @Test
    fun testDoorDashRepositoryTest() {
        mockStatic(Pager::class.java)
        Mockito.`when`(pager.flow).thenReturn(flow)
        testObject = object : DoorDashRepository(service) {
            override fun createPager(latitude: Double, longitude: Double) = pager
        }
        assertThat(testObject.getRestaurants(KNOWN_LATITUDE, KNOWN_LONGETUDE), `is`(flow))
    }
}