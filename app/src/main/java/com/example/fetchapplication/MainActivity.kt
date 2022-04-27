package com.example.fetchapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.fetchapplication.adapter.ItemAdapter
import com.example.fetchapplication.api.ApiInterface
import com.example.fetchapplication.data.myDataItem
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

const val BASE_URL = "https://fetch-hiring.s3.amazonaws.com/"
class MainActivity : AppCompatActivity() {
    lateinit var itemAdapter: ItemAdapter
    lateinit var linearLayoutManager: LinearLayoutManager
    lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        recyclerView = findViewById<RecyclerView>(R.id.recycler_view)
        recyclerView.setHasFixedSize(true)
        linearLayoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = linearLayoutManager
        getMyData(recyclerView)
    }

    private fun getMyData(recyclerView: RecyclerView){
        val retrofitBuilder = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
            .create(ApiInterface::class.java)

        val retrofitData = retrofitBuilder.getData()

        retrofitData.enqueue(object : Callback<List<myDataItem>?> {
            override fun onResponse(
                call: Call<List<myDataItem>?>,
                response: Response<List<myDataItem>?>
            ) {
                if(response.isSuccessful) {
                    val responseBody : MutableList<myDataItem> = response.body()!!.sortedWith(compareBy<myDataItem> {it.listId}.thenBy { it.name }).toMutableList()

//                    clearEmptyNames(responseBody)

                    itemAdapter = ItemAdapter(baseContext, responseBody)
                    itemAdapter.notifyDataSetChanged()
                    recyclerView.adapter = itemAdapter
                }
            }

            private fun clearEmptyNames(responseBody: MutableList<myDataItem>) {
                val listIterator = responseBody.iterator()
                while(listIterator.hasNext()) {
                    val item = listIterator.next()
                    if (item.name.isNullOrBlank()) {
                        listIterator.remove()
                    }
                }

            }

            override fun onFailure(call: Call<List<myDataItem>?>, t: Throwable) {
                Log.d("MainActivity", "OnFailure: " + t.message)
            }
        })
    }
}