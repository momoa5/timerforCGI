package com.example.timerforcgi.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.timerforcgi.controller.TwentyLapsController
import com.example.timerforcgi.databinding.ActivityTwentylapsBinding

class TwentyLapsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityTwentylapsBinding

    private var controller: TwentyLapsController? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTwentylapsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        controller = TwentyLapsController(this)


        controller?.loadData()
        binding.recViewTwenty.layoutManager = LinearLayoutManager(this)
        binding.recViewTwenty.adapter = controller?.getTimerAdapter()


    }

}