package com.example.jeonghyun.androidsunflowersample.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.jeonghyun.androidsunflowersample.data.GardenPlantingRepository

class GardenPlantingListViewModelFactory(
    private val repository : GardenPlantingRepository
) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return GardenPlantingListViewModel(repository) as T
    }
}