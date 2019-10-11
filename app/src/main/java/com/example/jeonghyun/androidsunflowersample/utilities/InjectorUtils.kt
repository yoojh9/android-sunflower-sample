package com.example.jeonghyun.androidsunflowersample.utilities

import android.content.Context
import com.example.jeonghyun.androidsunflowersample.data.AppDatabase
import com.example.jeonghyun.androidsunflowersample.data.GardenPlanting
import com.example.jeonghyun.androidsunflowersample.data.GardenPlantingRepository
import com.example.jeonghyun.androidsunflowersample.data.PlantRepository
import com.example.jeonghyun.androidsunflowersample.viewmodels.GardenPlantingListViewModel
import com.example.jeonghyun.androidsunflowersample.viewmodels.GardenPlantingListViewModelFactory
import com.example.jeonghyun.androidsunflowersample.viewmodels.PlantDetailViewModelFactory
import com.example.jeonghyun.androidsunflowersample.viewmodels.PlantListViewModelFactory

object InjectorUtils {

    private fun getPlantRepository(context: Context): PlantRepository {
        return PlantRepository.getInstance(AppDatabase.getInstance(context.applicationContext).plantDao())
    }

    private fun getGardenPlantingRepository(context: Context): GardenPlantingRepository {
        return GardenPlantingRepository.getInstance(AppDatabase.getInstance(context.applicationContext).gardenPlantingDao())
    }

    fun provideGardenPlantingListViewModelFactory(
        context: Context
    ) : GardenPlantingListViewModelFactory {
        val repository = getGardenPlantingRepository(context)
        return GardenPlantingListViewModelFactory(repository)
    }

    fun providePlantListViewModelFactory(context: Context): PlantListViewModelFactory {
        val repository = getPlantRepository(context)
        return PlantListViewModelFactory(repository)
    }

    fun providePlantDetailViewModelFactory(
        context: Context,
        plantId: String
    ) : PlantDetailViewModelFactory {
        return PlantDetailViewModelFactory(getPlantRepository(context),
            getGardenPlantingRepository(context), plantId)
    }
}