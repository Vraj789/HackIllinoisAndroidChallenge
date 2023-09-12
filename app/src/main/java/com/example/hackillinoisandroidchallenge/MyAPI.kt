package com.example.hackillinoisandroidchallenge

import retrofit2.http.GET
import retrofit2.Call

interface MyAPI {

    @GET("event/")
    fun getEvents(): Call<Schedule>
}