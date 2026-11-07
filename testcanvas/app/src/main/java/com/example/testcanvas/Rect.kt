package com.example.testcanvas

import android.graphics.Color

data class Rect(val x: Float, val y: Float, val width: Float, val height: Float, var filled: Boolean = false) {

    val color: Int
        get() = if(filled) Color.GREEN else Color.LTGRAY
    fun isInto(pointX: Float, pointY: Float ): Boolean = false

}