package com.example.jeonghyun.androidsunflowersample.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.jeonghyun.androidsunflowersample.data.PlantRepository

class PlantListViewModelFactory(
    private val repository: PlantRepository
) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>) = PlantListViewModel(repository) as T
}