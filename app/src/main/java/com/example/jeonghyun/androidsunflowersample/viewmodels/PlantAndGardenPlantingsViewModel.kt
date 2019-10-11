package com.example.jeonghyun.androidsunflowersample.viewmodels

import com.example.jeonghyun.androidsunflowersample.data.PlantAndGardenPlantings
import java.text.SimpleDateFormat
import java.util.*

class PlantAndGardenPlantingsViewModel(plantings: PlantAndGardenPlantings) {
    private val plant = checkNotNull(plantings.plant)
    private val gardenPlanting = plantings.gardenPlantings[0]

    val waterDateString = dateFormat.format(gardenPlanting.lastWateringDate.time)
    val wateringInterval
        get() = plant.wateringInterval
    val imageUrl
        get() = plant.imageUrl
    val plantName
        get() = plant.name
    val plantDateString = dateFormat.format(gardenPlanting.plantDate.time)
    val plantId
        get() = plant.plantId


    companion object {
        private val dateFormat = SimpleDateFormat("MMM d, yyyy", Locale.KOREA)
    }

}