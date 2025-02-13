package com.task.healthylife.model

data class DrList(
    val dr_list: List<ListOfDr>
)

data class ListOfDr(
    val id: Int,
    val doctorName: String,
    val date: String,
    val advice: String,
    val location: String,
    val backgroundColor: String
)
