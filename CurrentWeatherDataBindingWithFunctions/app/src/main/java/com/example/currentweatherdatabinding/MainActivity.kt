package com.example.currentweatherdatabinding

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.RadioButton
import android.widget.CheckBox
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.lifecycleScope
import com.example.currentweatherdatabinding.databinding.ActivityMainBinding
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.IOException
import java.net.HttpURLConnection
import java.net.URL
import java.net.URLEncoder


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: WeatherViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.vm = viewModel
        binding.lifecycleOwner = this

        binding.b1.setOnClickListener { onRadioButtonClicked(it) }
        binding.b2.setOnClickListener { onRadioButtonClicked(it) }
        binding.checkBox.setOnCheckedChangeListener { _, isChecked ->
            viewModel.setWind(isChecked)
        }
    }

    private suspend fun loadWeather() {
        val city = binding.cityArray.selectedItem?.toString() ?: return
        val apiKey = getString(R.string.api_key)
        val encodedCity = URLEncoder.encode(city, "UTF-8")
        val weatherURL =
            "https://api.openweathermap.org/data/2.5/weather?q=$encodedCity&appid=$apiKey&units=metric"

        try {
            val json = withContext(Dispatchers.IO) {
                val conn = URL(weatherURL).openConnection() as HttpURLConnection
                conn.connectTimeout = 10_000
                conn.readTimeout = 10_000
                if (conn.responseCode != 200) {
                    throw IOException("HTTP ${conn.responseCode}")
                }
                conn.inputStream.bufferedReader().use { it.readText() }
            }

            if (json.isBlank()) {
                Log.e("mytag", "Пустой ответ")
                return
            }
            Log.d("mytag", json)

            val data = Gson().fromJson(json, WeatherResponse::class.java)
            viewModel.setWeather(data)

        } catch (e: Exception) {
            Log.e("mytag", "Ошибка: ${e.message}")
        }
    }

    public fun onClick(v: View) {
        lifecycleScope.launch {
            loadWeather()
        }
    }

    fun onRadioButtonClicked(view: View) {
        if (view is RadioButton && view.isChecked) {
            when (view.id) {
                R.id.b1 -> viewModel.setUnits(false)
                R.id.b2 -> viewModel.setUnits(true)
            }
        }
    }

    fun onCheckBoxClicked(view: View){
        if (view is CheckBox && view.isChecked)
        {
            when (view.id){
                R.id.checkBox -> viewModel.setWind(true)
            }
        }
    }
}