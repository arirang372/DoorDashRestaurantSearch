package com.sung.doordash.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.sung.doordash.data.DoorDashRepository
import com.sung.doordash.model.Restaurant
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class RestaurantListViewModel @Inject constructor(
    private val repository: DoorDashRepository
) : ViewModel() {
    fun getRestaurants(latitude: Double, longitude: Double): Flow<PagingData<Restaurant>> {
        return repository.getRestaurants(latitude, longitude).cachedIn(viewModelScope)
    }
}