package com.example.fetch

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.RecyclerView
import com.example.fetch.adapter.ItemAdapter
import com.example.fetch.R

const val BASE_URL = "https://fetch-hiring.s3.amazonaws.com/"
lateinit var itemAdapter: ItemAdapter
private lateinit var recyclerView: RecyclerView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById<RecyclerView>(R.id.recycler_view)
        recyclerView.setHasFixedSize(true)

        GetData.getMyData(baseContext, recyclerView)
    }
}