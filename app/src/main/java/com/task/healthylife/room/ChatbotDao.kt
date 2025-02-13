package com.task.healthylife.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.task.healthylife.model.Message
import kotlinx.coroutines.flow.Flow

@Dao
interface ChatbotDao {
    @Insert
    suspend fun insertMessage(message: Message)

    @Insert
    suspend fun insertMessages(messages: List<Message>)

    @Query("SELECT * FROM messages ORDER BY id ASC")
    fun getAllMessages():Flow<List<Message>>

    @Query("DELETE FROM messages")
    suspend fun deleteAllMessages()

    @Delete
    suspend fun deleteMessage(message: Message)
}