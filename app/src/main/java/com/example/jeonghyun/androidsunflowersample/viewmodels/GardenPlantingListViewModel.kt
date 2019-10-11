package com.example.jeonghyun.androidsunflowersample.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.jeonghyun.androidsunflowersample.data.GardenPlantingRepository
import com.example.jeonghyun.androidsunflowersample.data.PlantAndGardenPlantings

class GardenPlantingListViewModel internal constructor(
    gardenPlantingRepository: GardenPlantingRepository
) : ViewModel() {
    val plantAndGardenPlantings : LiveData<List<PlantAndGardenPlantings>> =
        gardenPlantingRepository.getPlantedGardens()
}