package com.example.jeonghyun.androidsunflowersample.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.jeonghyun.androidsunflowersample.data.GardenPlantingRepository
import com.example.jeonghyun.androidsunflowersample.data.PlantRepository

class PlantDetailViewModelFactory(
    private val plantRepository: PlantRepository,
    private val gardenPlantingRepository: GardenPlantingRepository,
    private val plantId: String
) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return PlantDetailViewModel(plantRepository, gardenPlantingRepository, plantId) as T
    }
}