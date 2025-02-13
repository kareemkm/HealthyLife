package com.task.healthylife.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.task.healthylife.model.Message

@Database(entities = [Message::class], version = 1)
abstract class ChatbotDatabase: RoomDatabase() {
    abstract fun chatbotDao() : ChatbotDao

    companion object {
        @Volatile
        private var INSTANSE: ChatbotDatabase? = null

        fun getDatabase(context:Context): ChatbotDatabase {
            return INSTANSE ?: synchronized(this){
                val instance = Room.databaseBuilder(
                    context,
                    ChatbotDatabase::class.java,
                    "chatbot_database"
                ).build()
                INSTANSE = instance
                return instance
            }
        }
    }
}