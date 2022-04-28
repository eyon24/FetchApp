package com.example.fetch.api

import com.example.fetch.data.myDataItem
import retrofit2.Call
import retrofit2.http.GET

interface ApiInterface {

    @GET("hiring.json")
    fun getData(): Call<List<myDataItem>>

}