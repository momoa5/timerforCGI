package com.example.timerforcgi.model

import androidx.appcompat.app.AppCompatActivity
import com.example.timerforcgi.adapter.TimerAdapter
import com.example.timerforcgi.TimerData
import com.example.timerforcgi.controller.TwentyLapsController

class TwentyLapsModel(controller: TwentyLapsController) : AppCompatActivity(){
    private var controller: TwentyLapsController? = controller
    private var lastTwentyLapsList:ArrayList<TimerData> = ArrayList()
    var timerAdapter: TimerAdapter = TimerAdapter(this, lastTwentyLapsList)


     fun loadData() {
       // val sharedPreferences = getSharedPreferences("shared preferences", MODE_PRIVATE);

        for(i in 0 .. 20 step 1)
        {
            val value:String = controller?.sharedPreferences?.getString("position"+i, null).toString()

            if(value!="null")
                lastTwentyLapsList.add(TimerData(value))

        }

    }

}