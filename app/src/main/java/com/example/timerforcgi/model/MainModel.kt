package com.example.timerforcgi.model

import android.content.Intent
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.timerforcgi.adapter.TimerAdapter
import com.example.timerforcgi.TimerData
import com.example.timerforcgi.controller.MainController
import kotlin.math.roundToInt

class MainModel(controller: MainController) : AppCompatActivity(){
    private var controller: MainController? = controller
    private var timeStarted = false
    var timerList:ArrayList<TimerData> = ArrayList()
    var timerAdapter: TimerAdapter = TimerAdapter(this, timerList)

    fun startStopTime(serviceIntent: Intent)
    {
        if(timeStarted)
            stopTime(serviceIntent)
        else
            startTime(serviceIntent)
    }

    private fun startTime(serviceIntent: Intent) {
        controller?.startService(serviceIntent)
        controller?.bindingStopBtn()


        timeStarted = true
    }

    private fun stopTime(serviceIntent: Intent) {
        controller?.stopService(serviceIntent)
        controller?.bindingStartBtn()
        timeStarted = false
    }




    fun saveLap() {
        if(timeStarted) {
            var i = getPosition()

            if(i == 20)
                i = 0

            timerList.add(TimerData(controller?.getTimeTxtView().toString()))
            saveData(controller?.getTimeTxtView().toString(), i)
            timerAdapter.notifyDataSetChanged()
           // controller?.onNotifyDataSetChanged()
        }
    }

    private fun getPosition():Int
    {
        Log.i("output", controller?.sharedPreferences!!.getInt("counter",0).toString())
        return controller?.sharedPreferences!!.getInt("counter",0)
    }

    private fun saveData(value: String, i: Int) {
        val editor = controller?.sharedPreferences?.edit()

        Log.i("output", value)
        editor?.putString("position" + i, value)
        editor?.putInt("counter", i+1)
        editor?.apply()


    }

     fun resetTime(serviceIntent: Intent) {
         controller?.resetService(serviceIntent)
         controller?.bindingStartBtn()
         timeStarted = false

        timerList.clear()
         timerAdapter.notifyDataSetChanged()
       // controller?.onNotifyDataSetChanged()
    }


     fun getTimeStringFromDlouble(time: Double): String {

        val resultInt = time.roundToInt()
        val minutes = resultInt % 86400 / 6000
        val seconds = resultInt % 86400 % 6000 / 100
        val miliseconds = resultInt % 86400 % 3600 % 100


        return makeTimeString(minutes, seconds, miliseconds)

    }

    private fun makeTimeString(minutes: Int, seconds: Int, miliseconds: Int): String {
        return String.format("%02d:%02d.%02d", minutes, seconds, miliseconds)
    }
}