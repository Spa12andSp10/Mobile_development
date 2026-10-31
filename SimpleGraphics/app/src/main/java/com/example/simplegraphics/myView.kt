package com.example.snowflakesk

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.os.AsyncTask
import android.util.Log
import android.view.MotionEvent
import android.view.View
import kotlin.random.Random
import kotlin.random.nextInt

data class Snowflake(var x: Float, var y: Float, val velocity: Float, val radius: Float, val color: Int, var coeff: Float)
lateinit var snow: Array<Snowflake>
val paint = Paint()
var h = 1000; var w = 1000 // значения по умолчанию, будут заменены в onLayout()

open class Snowflakes(ctx: Context) : View(ctx) {
//    lateinit var moveTask: MoveTask
    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        // перерисовывает канву
        canvas.drawColor(Color.BLUE)

        for (s in snow) {
            paint.color = s.color
            canvas.drawCircle(s.x, s.y, s.radius, paint)
        }

    }

    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        super.onLayout(changed, left, top, right, bottom)
        h = bottom - top; w = right - left
        val r = Random(0)
        snow = Array(10) { Snowflake(x = r.nextInt(0, w).toFloat(), y =  r.nextInt(0, h).toFloat(),
            velocity = (15 + 10 * r.nextInt(0, 10)).toFloat(), radius = (30 + 20 * r.nextInt(0, 3)).toFloat(),
            Color.rgb(Random.nextInt(220, 255),Random.nextInt(220, 255),Random.nextInt(220, 255)),
            coeff = 1.0f)
        }
        Log.d("mytag", "snow: " + snow.contentToString())
    }

    fun moveSnowflakes() {
        for (s in snow) {
            s.coeff = (1f - s.y / h).coerceIn(0.1f, 1f)
            s.y += s.velocity * s.coeff

            if (s.y > h) {
                s.y -= h
                s.coeff = 1.0f// если снежинка покинула экран, вычитаем высоту
            }
        }
        invalidate() // перерисовать
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        moveSnowflakes()
        invalidate() // перерисовать View
//        moveTask = MoveTask(this)
//        moveTask.execute(100)
        return false // защита от множественных срабатываний

    }
//    class MoveTask(val s: Snowflakes) : AsyncTask<Int,Int,Int>() {
//        // https://developer.alexanderklimov.ru/android/theory/asynctask.php
//        override fun doInBackground(vararg params: Int?): Int {
//            val delay = params[0] ?: 200 // если задерка не задана
//            while (true) {
//                Thread.sleep(delay.toLong())
//                s.moveSnowflakes()
//            }
//            return 0
//        }
//    }
}