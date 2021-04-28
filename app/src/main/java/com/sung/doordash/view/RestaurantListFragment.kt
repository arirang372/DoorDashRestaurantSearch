package com.sung.doordash.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.sung.doordash.data.KNOWN_LATITUDE
import com.sung.doordash.data.KNOWN_LONGETUDE
import com.sung.doordash.databinding.RestaurantListFragmentBinding
import com.sung.doordash.viewmodel.RestaurantListViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class RestaurantListFragment : Fragment() {
    private val adapter = RestaurantListAdapter()
    private var job: Job? = null
    private val viewModel: RestaurantListViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = RestaurantListFragmentBinding.inflate(inflater, container, false)
        context ?: return binding.root
        binding.restaurantsRecyclerView.adapter = adapter
        searchRestaurants()
        return binding.root
    }

    private fun searchRestaurants() {
        job?.cancel()
        job = lifecycleScope.launch {
            viewModel.getRestaurants(KNOWN_LATITUDE, KNOWN_LONGETUDE).collectLatest {
                adapter.submitData(it)
            }
        }
    }
}