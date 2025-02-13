package com.task.healthylife.classes

import com.task.healthylife.R
import com.task.healthylife.model.Task

class TaskList {

    val tasks:List<Task> = listOf(
        Task(
            image = R.drawable.noun_run_4787609,
            label = "Walk for 15 Minutes",
            description = "Take a short walking break to boost circulation and energize your body.",
            isComplete = false
        ),
        Task(
            image = R.drawable.noun_glass_4993285,
            label = "Drink a Glass of Water",
            description = "Keep yourself hydrated by drinking a glass of water every two hours",
            isComplete = false
        ),
        Task(
            image = R.drawable.noun_food_7047719,
            label = "Eat a Healthy Snack",
            description = "Choose a snack like fruits or nuts to replenish your energy in a healthy way.",
            isComplete = false
        ),
        Task(
            image = R.drawable.noun_sun_7371074,
            label = "Get Some Sunlight",
            description = "Get a dose of Vitamin D by sitting in the sun for 10 minutes.",
            isComplete = false
        ),
        Task(
            image = R.drawable.noun_breathing_7305039,
            label = "Practice Deep Breathing",
            description = "Take 5 minutes to do deep breathing exercises to reduce stress.",
            isComplete = false
        ),
        Task(
            image = R.drawable.noun_stretching_exercise_2383411,
            label = "Quick Stretching Exercise",
            description = "Stretch your muscles to relieve tension, especially if you've been sitting for a long time.",
            isComplete = false
        ),
        Task(
            image = R.drawable.noun_balanced_diet_5133635,
            label = "Eat a Balanced Meal",
            description = "Make sure your meal includes protein and vegetables to keep your body well-nourished.",
            isComplete = false
        ),
        Task(
            image = R.drawable.noun_cafe_882203,
            label = "Short Reading Break",
            description = "Take a few minutes to read something enjoyable or educational to relax and recharge.",
            isComplete = false
        ),

    )
}