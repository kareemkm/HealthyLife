package com.task.healthylife.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.task.healthylife.model.StepRecord

@Database(entities = [StepRecord::class], version = 1)
abstract class StepRecordDatabase: RoomDatabase(){

    abstract fun stepRecordDao(): StepRecordDao

    companion object{
        @Volatile
        private var INSTANSE:StepRecordDatabase? = null

        fun getDatabase(context: Context):StepRecordDatabase{
            return INSTANSE ?: synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    StepRecordDatabase::class.java,
                    "database"
                ).build()
                INSTANSE = instance
                return instance
            }
        }
    }
}