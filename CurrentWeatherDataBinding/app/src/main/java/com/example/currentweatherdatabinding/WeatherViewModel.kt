package com.example.currentweatherdatabinding

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class WeatherViewModel(): ViewModel() {

    private val _weather = MutableLiveData<WeatherResponse?>()
    val weather: LiveData<WeatherResponse?> = _weather

    private val _error = MutableLiveData<String?>()
    val error: LiveData<String?> = _error

    fun setWeather(data: WeatherResponse?) {_weather.value = data}
    fun setError(msg: String?) {_error.value = msg}
}