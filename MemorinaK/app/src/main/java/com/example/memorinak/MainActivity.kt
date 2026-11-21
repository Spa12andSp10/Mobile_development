package com.example.memorinak


import android.app.ActionBar
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.AttributeSet
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val layout = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
        }

        val legions = arrayOf(
            "ultramarines", "death_guard", "emperors_children", "raven_guard",
            "salamanders", "thousand_sons", "white_scars", "world_eaters"
        )
        val drawables = mapOf(
            "ultramarines" to R.drawable.ultramarines,
            "death_guard" to R.drawable.death_guard,
            "emperors_children" to R.drawable.emperors_children,
            "raven_guard" to R.drawable.raven_guard,
            "salamanders" to R.drawable.salamanders,
            "thousand_sons" to R.drawable.thousand_sons,
            "white_scars" to R.drawable.white_scars,
            "world_eaters" to R.drawable.world_eaters
        )

        val deck = legions.flatMap { listOf(it, it) }.shuffled()
        val rows = Array(4) { LinearLayout(this) }

        deck.forEachIndexed { index, legion ->
            val iv = ImageView(this).apply {
                setBackgroundResource(R.drawable.warhammer_logo)
                setImageResource(drawables.getValue(legion))
                imageAlpha = 0
                alpha = 1f
                layoutParams = LinearLayout.LayoutParams(
                    0, LinearLayout.LayoutParams.WRAP_CONTENT
                ).apply { weight = 1f }
                tag = legion
                setOnClickListener { openCard(it as ImageView) }
            }
            rows[index / 4].addView(iv)
        }

        rows.forEach { layout.addView(it) }
        setContentView(layout)
    }

    suspend fun setBackgroundWithDelay(v: View) {
        delay(1000)
        v.setBackgroundColor(Color.YELLOW)
        delay(1000)
        v.visibility = View.INVISIBLE
        v.isClickable = false
    }

    private fun openCard(v: ImageView) {
        if(!v.isClickable) return
        v.isClickable = false
        v.background = null
        v.imageAlpha = 255
        v.postDelayed({
            v.animate()
                .alpha(0f)
                .setDuration(300)
                .withEndAction { v.visibility = View.GONE }
                .start()
        }, 1000)
    }

    // обработчик нажатия на кнопку
    val colorListener = View.OnClickListener() {
        // запуск функции в фоновом потоке
        GlobalScope.launch (Dispatchers.Main)
        { setBackgroundWithDelay(it) }
    }
}