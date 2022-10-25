package com.example.timerforcgi.view

import android.content.*
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.timerforcgi.*
import com.example.timerforcgi.controller.MainController
import com.example.timerforcgi.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    private lateinit var serviceIntent: Intent
    private var time = 0.0

    private var controller: MainController? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        controller = MainController(this)

        binding.recView.layoutManager = LinearLayoutManager(this)
        binding.recView.adapter = controller?.getTimerAdapter()

        binding.btnStartStop.setOnClickListener{controller?.setStartStopTime(serviceIntent)}
        binding.btnLap.setOnClickListener{controller?.setSaveLap()}
        binding. btnReset.setOnClickListener{controller?.setResetTime(serviceIntent)}
        binding.btnTwenty.setOnClickListener{startNewActivity()}

        serviceIntent = Intent(applicationContext, TimeService::class.java)
        registerReceiver(updateTime, IntentFilter(TimeService.TIMER_UPDATER))

    }

    private fun startNewActivity() {
        val intent = Intent(this, TwentyLapsActivity::class.java)
        startActivity(intent)
    }

    fun startServices(serviceIntent: Intent)
    {
        serviceIntent.putExtra(TimeService.TIME_EXTRA, time)
        startService(serviceIntent)
    }

    fun stopServices(serviceIntent: Intent)
    {
        stopService(serviceIntent)
    }

    fun resetServices(serviceIntent: Intent)
    {
        stopService(serviceIntent)
        time = 0.0
        binding. txtViewTime.text = controller?.getTimeStringFromDlouble(time)
    }


    private val updateTime: BroadcastReceiver = object  : BroadcastReceiver()
    {
        override fun onReceive(context: Context, intent: Intent) {
            time = intent.getDoubleExtra(TimeService.TIME_EXTRA, 0.0)
            binding. txtViewTime.setText(controller?.getTimeStringFromDlouble(time))
        }
    }

}