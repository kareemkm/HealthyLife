package com.task.healthylife.model

data class Task(
    val image:Int,
    val label:String,
    val description:String,
    var isComplete:Boolean
)
