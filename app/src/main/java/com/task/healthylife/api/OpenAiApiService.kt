package com.task.healthylife.api

import com.task.healthylife.model.ChatRequest
import com.task.healthylife.model.ChatResponse
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST

interface OpenAiApiService {

    @POST ("v1/chat/completions")
    suspend fun getCompletion(
        @Header ("Authorization") authHeader: String,
        @Body request: ChatRequest
    ): ChatResponse

}

object RetrofitInstanceOpenAi {
    val api: OpenAiApiService by lazy {
        Retrofit
            .Builder()
            .baseUrl("https://api.openai.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(OpenAiApiService::class.java)
    }
}