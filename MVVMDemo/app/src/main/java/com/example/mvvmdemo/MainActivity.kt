package com.example.mvvmdemo

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer

import androidx.lifecycle.ViewModelProvider


class MainActivity : AppCompatActivity() {

    lateinit var viewModel: MainViewModel

    private lateinit var textCounter: TextView
    private lateinit var btnIncrement: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        textCounter = findViewById(R.id.text_counter)
        btnIncrement = findViewById(R.id.btn_increment)

        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        observeViewModel()

        initView()
    }

    fun observeViewModel() {
       viewModel.counter.observe(this, Observer{ value ->
           textCounter.text = value.toString()
       })
    }

    fun initView() {
        btnIncrement.setOnClickListener {
            viewModel.onIncrementClicked()
        }
    }
}