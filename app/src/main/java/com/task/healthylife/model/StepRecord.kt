package com.task.healthylife.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "step_records")
data class StepRecord(
    @PrimaryKey(autoGenerate = true) val id:Int = 0,
    val day: String,
    val stepCount: Int
)