package com.task.healthylife.viewModel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.task.healthylife.api.RetrofitInstanceOpenAi
import com.task.healthylife.classes.ApiKeyProvider
import com.task.healthylife.model.ChatRequest
import com.task.healthylife.model.Message
import com.task.healthylife.room.ChatbotDatabase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ChatbotViewModel(application: Application) : AndroidViewModel(application) {

    private val chatbotDao = ChatbotDatabase.getDatabase(application).chatbotDao()

    private val _chatMessages = MutableStateFlow<List<Message>>(emptyList())
    val chatMessages: StateFlow<List<Message>> = _chatMessages

    private val apiKey = ApiKeyProvider.getApiKey()
    init {
        viewModelScope.launch {
            chatbotDao.getAllMessages().collect { message ->
                _chatMessages.value = message
            }
        }
    }

    fun sendMessage(userMessage: String) {
        viewModelScope.launch {
            try {
                chatbotDao.insertMessage(Message(role = "user", content = userMessage))
                val updatedMessages =
                    _chatMessages.value + Message(role = "user", content = userMessage)
                _chatMessages.value = updatedMessages

                val request = ChatRequest(
                    messages = listOf(
                        Message(
                          role = "user",
                            content = "$messageToFilterSearch , $userMessage"
                        ),

                    )
                )

                val response =
                    RetrofitInstanceOpenAi.api.getCompletion(authHeader = apiKey, request = request)


                val botResponse = response.choices.first().message.content

                chatbotDao.insertMessage(Message(role = "assistant", content = botResponse))

                _chatMessages.value =
                    updatedMessages + Message(role = "assistant", content = botResponse)
            } catch (e: Exception) {
                Log.e("ChatbotError", "${e.message}", e)
            }
        }
    }

    private val messageToFilterSearch =
        "You are a chatbot dedicated to questions in healthy life and any questions other than that I apologize to him"

    fun deleteAllMessages() {
        viewModelScope.launch {
            chatbotDao.deleteAllMessages()
        }
    }

    fun deleteMessage(message: Message) {
        viewModelScope.launch {
            chatbotDao.deleteMessage(message)
        }
    }
}