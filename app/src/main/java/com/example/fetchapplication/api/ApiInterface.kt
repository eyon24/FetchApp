package com.example.fetchapplication.api

import com.example.fetchapplication.data.myDataItem
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {

    @GET("hiring.json")
    fun getData(): Call<List<myDataItem>>

}