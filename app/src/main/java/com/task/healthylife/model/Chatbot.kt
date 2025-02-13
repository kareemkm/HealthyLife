package com.task.healthylife.model

import androidx.room.Entity
import androidx.room.PrimaryKey


data class ChatRequest(
    val model: String = "gpt-3.5-turbo",
    val messages: List<Message>
)

@Entity(tableName = "messages")
data class Message(
    @PrimaryKey(autoGenerate = true) val id: Int = 0 ,
    val role: String,
    val content: String
)

data class ChatResponse(
    val choices: List<Choice>
)

data class Choice(
    val message: Message
)
