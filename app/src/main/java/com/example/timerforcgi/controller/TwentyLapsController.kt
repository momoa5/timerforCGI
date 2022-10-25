package com.example.timerforcgi.controller

import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import com.example.timerforcgi.adapter.TimerAdapter
import com.example.timerforcgi.model.TwentyLapsModel

import com.example.timerforcgi.view.TwentyLapsActivity

class TwentyLapsController() {


    private var view: TwentyLapsActivity? = null
    private var model: TwentyLapsModel? = null
    var sharedPreferences: SharedPreferences? = null

    constructor(view: TwentyLapsActivity) : this() {
        this.view = view
        model = TwentyLapsModel(this)
        sharedPreferences = view.getSharedPreferences("shared preferences", AppCompatActivity.MODE_PRIVATE);

    }

    fun loadData() {
        model?.loadData()
    }

    fun getTimerAdapter(): TimerAdapter {
        return model!!.timerAdapter
    }

}