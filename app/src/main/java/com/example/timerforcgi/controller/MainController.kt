package com.example.timerforcgi.controller

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import com.example.timerforcgi.model.MainModel
import com.example.timerforcgi.R
import com.example.timerforcgi.adapter.TimerAdapter
import com.example.timerforcgi.view.MainActivity

class MainController() {
    private var view: MainActivity? = null
    private var model: MainModel? = null
    var sharedPreferences: SharedPreferences? = null

    constructor(view: MainActivity) : this() {
        this.view = view
        model = MainModel(this)
        sharedPreferences = view.getSharedPreferences("shared preferences", AppCompatActivity.MODE_PRIVATE);

    }

    fun setStartStopTime(serviceIntent: Intent)
    {
        model?.startStopTime(serviceIntent)
    }

    fun setSaveLap()
    {
        model?.saveLap()
    }

    fun setResetTime(serviceIntent: Intent) {
        model?.resetTime(serviceIntent)
    }

    fun startService(serviceIntent: Intent)
    {
        this.view?.startServices(serviceIntent)

    }

    fun stopService(serviceIntent: Intent)
    {
        this.view?.stopServices(serviceIntent)

    }

    fun resetService(serviceIntent: Intent) {
        this.view?.resetServices(serviceIntent)
    }


    fun bindingStopBtn()
    {
        this.view?.binding?.btnStartStop?.text = "Stop"
        this.view?.binding?.btnStartStop?.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_baseline_pause, 0, 0, 0)
    }


    fun bindingStartBtn()
    {
        this. view?.binding?.btnStartStop?.text = "Start"
        this. view?.binding?.btnStartStop?.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_baseline_start, 0, 0, 0)
    }

    fun getTimeTxtView():String
    {
       return this.view?.binding?.txtViewTime?.text.toString()
    }

    fun getTimerAdapter(): TimerAdapter {
        return model!!.timerAdapter
    }

    fun getTimeStringFromDlouble(time: Double): CharSequence? {
        return model?.getTimeStringFromDlouble(time)
    }

  /*  fun onNotifyDataSetChanged() {
        this.view?.timerAdapter?.notifyDataSetChanged()
    }*/


}