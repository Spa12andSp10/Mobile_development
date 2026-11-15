package com.example.currentweatherdatabinding

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.lifecycle.LiveData

@BindingAdapter("cloudIcon")
fun setCloudIcon(view: ImageView, weather: LiveData<WeatherResponse?>)
{
    val main = weather.value?.weather?.firstOrNull()?.main
    val res = when (main) {
        "Clear" -> R.drawable.ic_sun
        "Clouds" -> R.drawable.ic_cloud
        "Rain", "Drizzle" -> R.drawable.ic_rain
        "Snow" -> R.drawable.ic_snow
        "Thunderstorm" -> R.drawable.ic_storm
        else -> R.drawable.ic_cloud
    }
    view.setImageResource(res)
}

@BindingAdapter("windIcon")
fun setWindIcon(view: ImageView, weather: LiveData<WeatherResponse?>)
{
    val deg = weather.value?.wind?.deg ?: 0.0
    view.setImageResource(R.drawable.ic_arrow)
    view.rotation = (deg + 180).toFloat()
}
