package com.example.fetchapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.fetchapplication.adapter.ItemAdapter
import com.example.fetchapplication.api.ApiInterface
import com.example.fetchapplication.data.myData
import com.example.fetchapplication.data.myDataItem
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

const val BASE_URL = "https://fetch-hiring.s3.amazonaws.com/"
class MainActivity : AppCompatActivity() {
    private lateinit var itemAdapter: ItemAdapter
    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var recyclerView: RecyclerView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById<RecyclerView>(R.id.recycler_view)
        recyclerView.setHasFixedSize(true)

        getMyData(recyclerView)
    }

    /** Uses the ApiIterface class to get data from the url provided,
     *  sorts the data by listId and name,
     *  then removes any items where the name is "" or Null
     *
     *  @param recyclerView the RecyclerView to display items on
     *
     * */
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

                    // Put the response List into a mutable list that is sorted
                    val responseBody : MutableList<myDataItem> = response.body()!!
                        .sortedWith(compareBy<myDataItem> {it.listId}.thenBy { it.name })
                        .toMutableList()

                    clearEmptyNames(responseBody)

                    // Send new mutableList to Adapter Class
                    itemAdapter = ItemAdapter(baseContext, responseBody)

                    //  set adapter to our recyclerView
                    recyclerView.adapter = itemAdapter

                } else {
                    Log.d("Response", "Failed Response")
                }
            }

            override fun onFailure(call: Call<List<myDataItem>?>, t: Throwable) {
                Log.d("MainActivity", "OnFailure: " + t.message)
            }

        })
    }

    /** Iterates through a list and removes any items with a name that is "" or Null
     *
     *  @param responseBody - mutable list to iterate through
     * */
    private fun clearEmptyNames(responseBody: MutableList<myDataItem>) {
        val listIterator = responseBody.iterator()

        while(listIterator.hasNext()) {
            val item = listIterator.next()

            // if the name is "" or Null it is removed
            if (item.name.isBlank()) {
                listIterator.remove()
            }
        }
    }
}