package com.example.jeonghyun.androidsunflowersample.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jeonghyun.androidsunflowersample.data.GardenPlantingRepository
import com.example.jeonghyun.androidsunflowersample.data.PlantRepository
import kotlinx.coroutines.launch

class PlantDetailViewModel(
    plantRepository: PlantRepository,
    private val gardenPlantingRepository: GardenPlantingRepository,
    private val plantId: String
): ViewModel() {

    val isPlanted = gardenPlantingRepository.isPlanted(plantId)
    val plant = plantRepository.getPlant(plantId)

    fun addPlantToGarden(){
        viewModelScope.launch {
            gardenPlantingRepository.createGardenPlanting(plantId)
        }
    }

}
