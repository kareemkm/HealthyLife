package com.task.healthylife.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.task.healthylife.model.StepRecord
import kotlinx.coroutines.flow.Flow

@Dao
interface StepRecordDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertStepRecorde(stepRecord: StepRecord)


    @Query("SELECT * FROM step_records WHERE day = :day LIMIT 1")
    suspend fun getStepRecordByDate(day: String): StepRecord?


    @Query("SELECT * FROM step_records ORDER BY day DESC")
    fun getAllStepsRecorde(): Flow<List<StepRecord>>


    @Update
    suspend fun updateStepRecord(stepRecord: StepRecord)


    @Delete
    suspend fun deleteCountStep(stepRecord: StepRecord)
}