package com.task.healthylife.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.task.healthylife.model.UserInformation

@Database(entities = [UserInformation::class], version = 1)
abstract class UserInformationDatabase:RoomDatabase() {

    abstract fun userInformationDao(): UserInformationDao

    companion object {
        @Volatile
        private var INSTANSE:UserInformationDatabase? = null
        fun getDatabase(context: Context): UserInformationDatabase {
            return INSTANSE ?: synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    UserInformationDatabase::class.java,
                    "user"
                ).build()
                INSTANSE = instance
                instance
            }
        }
    }

}