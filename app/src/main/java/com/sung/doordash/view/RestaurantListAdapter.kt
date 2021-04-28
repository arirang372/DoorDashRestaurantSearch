package com.sung.doordash.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import com.sung.doordash.databinding.RestaurantListItemBinding
import com.sung.doordash.model.Restaurant

class RestaurantListAdapter :
    PagingDataAdapter<Restaurant, RestaurantViewHolder>(RestaurantDiffCallback()) {

    override fun onBindViewHolder(holder: RestaurantViewHolder, position: Int) {
        getItem(position)?.let { holder.bind(it) }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RestaurantViewHolder {
        return RestaurantViewHolder(
            RestaurantListItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }
}
