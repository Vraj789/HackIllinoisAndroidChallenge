package com.example.hackillinoisandroidchallenge

data class Events (

    val id: String,
    val name: String,
    val description: String,
    val startTime: Int,
    val endTime: Int,
    val eventType: String,
    val locations: List<location>

)
