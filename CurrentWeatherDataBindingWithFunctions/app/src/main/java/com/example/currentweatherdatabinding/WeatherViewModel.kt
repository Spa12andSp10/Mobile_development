package com.example.currentweatherdatabinding

import androidx.databinding.ObservableBoolean
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class WeatherViewModel(): ViewModel() {

    private val _weather = MutableLiveData<WeatherResponse?>()
    val weather: LiveData<WeatherResponse?> = _weather

    val useFahrenheit = ObservableBoolean(false)
    val withWind = ObservableBoolean(false)

    private val _error = MutableLiveData<String?>()
    val error: LiveData<String?> = _error

    fun setWeather(data: WeatherResponse?) {_weather.value = data}
    fun setError(msg: String?) {_error.value = msg}
    fun setUnits(fahrenheit: Boolean){
        useFahrenheit.set(fahrenheit)
    }
    fun setWind(wind: Boolean){
        withWind.set(wind)
    }
}