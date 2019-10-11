package com.example.jeonghyun.androidsunflowersample.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName="plants")
data class Plant(
    @PrimaryKey @ColumnInfo(name="id") val plantId: String,
    val name: String,
    val description: String,
    val growZoneNumber: Int,
    val wateringInterval: Int = 7,
    val imageUrl: String = ""

) {

    // apply() 함수는 블록에 객체 자신이 리시버 객체로 전달되고 이 객체가 반환된다.
    // 객체의 상태를 변화시키고 그 객체를 다시 반환할 때 주로 사용합니다.
    fun shouldBeWatered(since: Calendar, lastWateringDate: Calendar) =
        since > lastWateringDate.apply { add(Calendar.DAY_OF_YEAR, wateringInterval) }

    override fun toString() = name
}