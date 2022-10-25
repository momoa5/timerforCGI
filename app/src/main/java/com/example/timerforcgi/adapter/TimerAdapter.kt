package com.example.timerforcgi.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.timerforcgi.R
import com.example.timerforcgi.TimerData

class TimerAdapter(val c:Context, val timeList:ArrayList<TimerData>) : RecyclerView.Adapter<TimerAdapter.TimerViewHolder>() {

    inner class TimerViewHolder(val v: View):RecyclerView.ViewHolder(v){
        val txtViewTime = v.findViewById<TextView>(R.id.txt_time)
        val TxtViewRound = v.findViewById<TextView>(R.id.txt_round)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TimerViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val v = inflater.inflate(R.layout.list_item,parent,false)
        return TimerViewHolder(v)
    }

    override fun onBindViewHolder(holder: TimerViewHolder, position: Int) {
        val newList = timeList[position]
        holder.TxtViewRound.text = (position+1).toString()
        holder.txtViewTime.text = newList.time
    }

    override fun getItemCount(): Int {
        return timeList.size
    }

}