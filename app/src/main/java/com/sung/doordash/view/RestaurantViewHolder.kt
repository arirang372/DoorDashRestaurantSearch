package com.sung.doordash.view

import androidx.recyclerview.widget.RecyclerView
import com.sung.doordash.databinding.RestaurantListItemBinding
import com.sung.doordash.model.Restaurant

class RestaurantViewHolder(
    private val binding: RestaurantListItemBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(item: Restaurant) {
        binding.model = item
        binding.executePendingBindings()
    }
}