package com.task.healthylife.api

import com.task.healthylife.model.DietPlan
import com.task.healthylife.model.DrList
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface ApiService {

    @GET("data_paln_food.json")
    suspend fun getDietPlan(): DietPlan

    @GET("dr_list.json")
    suspend fun getDrList(): DrList
}

object RetrofitInstance {

    val api:ApiService by lazy {
        Retrofit
            .Builder()
            .baseUrl("https://kareemkm.github.io/healty_life_data/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }
}