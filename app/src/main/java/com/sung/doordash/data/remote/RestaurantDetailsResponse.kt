package com.sung.doordash.data.remote

import com.sung.doordash.model.Menu

data class RestaurantDetailsResponse(
    val menus: List<Menu>
)