package com.example.hackillinoisandroidchallenge

import android.os.Build
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import java.time.Instant

class RecyclerAdapter(private var allEventList: List<Events>) : RecyclerView.Adapter<RecyclerAdapter.ViewHolder>() {

    private val TAG: String = "CHECK_RESPONSE"

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val eventName: TextView
        var eventDesc: TextView
        val eventTime: TextView
        val eventType: TextView
        val eventLocation: TextView

        init {
            eventName = itemView.findViewById(R.id.eventName)
            eventDesc = itemView.findViewById(R.id.eventDesc)
            eventTime = itemView.findViewById(R.id.eventTime)
            eventType = itemView.findViewById(R.id.eventType)
            eventLocation = itemView.findViewById(R.id.eventLocation)

            itemView.setOnClickListener { v: View ->
                val position: Int = adapterPosition
                Toast.makeText(itemView.context, "You clicked on item # ${position + 1}" , Toast.LENGTH_SHORT).show()
            }
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerAdapter.ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.each_event, parent, false)
        return ViewHolder(v)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: RecyclerAdapter.ViewHolder, position: Int) {
        holder.eventName.text = allEventList[position].name
        holder.eventDesc.text = allEventList[position].description
        Log.i(TAG, allEventList[position].startTime.toString())
        holder.eventTime.text = getDateTime(allEventList[position].startTime, allEventList[position].endTime)
        holder.eventType.text = allEventList[position].eventType
        if (allEventList[position].locations.isNotEmpty()) {
            holder.eventLocation.text = allEventList[position].locations[0].description
        }

    }

    override fun getItemCount(): Int {
        return allEventList.size
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun getDateTime(start: Int, end: Int): String? {
        return try {
            val startTime = Instant.ofEpochSecond(start.toLong())
            var startTimeString = startTime.toString()
            val endTime = Instant.ofEpochSecond(end.toLong())
            val endTimeString = endTime.toString()
            val time = startTimeString.substring(5, 10) + " (" + startTimeString.substring(11, 16) + " - " + endTimeString.substring(11,16) + ") "
            return time
        } catch (e: Exception) {
            e.toString()
        }
    }



}
