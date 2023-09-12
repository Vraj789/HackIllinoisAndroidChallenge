package com.example.hackillinoisandroidchallenge

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.activity.ComponentActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/* Create a schedule page that displays all event details for the hackathon!
 * Make a GET API call to the HackIllinois API event endpoint
 * Recommended: use Retrofit to create HTTP requests
 * Remember to add the libraries you want to use to your build.gradle file!
*/

class MainActivity : ComponentActivity() {

    private val BASE_URL = "https://adonix.hackillinois.org/"
    private val TAG: String = "CHECK_RESPONSE"
    private var allEventsList: List<Events> = mutableListOf<Events>()
    private lateinit var eventsRecyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        eventsRecyclerView = findViewById(R.id.eventsRecyclerView)


        getAllEvents()

    }

    private fun getAllEvents() {
        val api = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(MyAPI::class.java)
        api.getComments().enqueue(object : Callback<Event>{
            override fun onResponse(call: Call<Event>, response: Response<Event>) {
                if (response.isSuccessful) {
                    allEventsList = response.body()!!.events
                    eventsRecyclerView.layoutManager = LinearLayoutManager(this@MainActivity)
                    eventsRecyclerView.adapter = RecyclerAdapter(allEventsList)
                }
            }

            override fun onFailure(call: Call<Event>, t: Throwable) {
                Log.i(TAG, "onResponse: ${t.message}")
            }


        })
    }

}
