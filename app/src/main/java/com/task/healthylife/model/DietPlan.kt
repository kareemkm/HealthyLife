package com.task.healthylife.model

data class DietPlan (
    val dietPlans: Weight
)


data class Weight(
    val weight_loss: DietDetails,
    val weight_gain: DietDetails,
    val weight_maintenance: DietDetails
)

data class DietDetails(
    val days: List<Day>
)

data class Day(
    var day: Int,
    val meals: List<Meal>,
    val day_total_calories: Int
)

data class Meal(
    val name: String,
    val items: List<FoodItem>,
    val total_calories: Int
)

data class FoodItem(
    val food:String,
    val calories: Int,
    val quantity: String,
    val recipe:String,
    val image_url: String
)

