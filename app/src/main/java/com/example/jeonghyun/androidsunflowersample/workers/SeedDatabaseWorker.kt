package com.example.jeonghyun.androidsunflowersample.workers

import android.content.Context
import com.google.gson.stream.JsonReader
import android.util.Log
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.example.jeonghyun.androidsunflowersample.data.AppDatabase
import com.example.jeonghyun.androidsunflowersample.data.Plant
import com.example.jeonghyun.androidsunflowersample.utilities.PLANT_DATA_FILENAME
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.coroutineScope

class SeedDatabaseWorker(
    context: Context,
    workerParams: WorkerParameters
): CoroutineWorker(context, workerParams) {
    override suspend fun doWork(): Result = coroutineScope {
        try {
            // Closeable 인터페이스가 구현된 클래스에 한해 use를 사용하실 수 있다. 내부 구현을 보면 Exception이 발생하거나 말거나 항상 close()를 호출을 보장한다.
            applicationContext.assets.open(PLANT_DATA_FILENAME).use { inputStream ->
                JsonReader(inputStream.reader()).use { jsonReader ->
                    val plantType = object : TypeToken<List<Plant>>() {}.type
                    val plantList: List<Plant> = Gson().fromJson(jsonReader, plantType)

                    val database = AppDatabase.getInstance(applicationContext)
                    database.plantDao().insertAll(plantList)

                    Result.success()
                }
            }
        } catch(ex: Exception) {
            Log.e(TAG, "Error Seeding database", ex)
            Result.failure()
        }
    }

    companion object {
        private val TAG = SeedDatabaseWorker::class.java.simpleName
    }


}