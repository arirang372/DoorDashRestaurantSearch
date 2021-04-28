package com.sung.doordash.data.remote

import com.google.gson.annotations.SerializedName
import com.sung.doordash.model.Restaurant

data class SearchRestaurantsResponse(
    @field:SerializedName("num_results") val numberOfResults: Int,
    @field:SerializedName("next_offset") val nextOffSet: Int,
    val stores: List<Restaurant>
)