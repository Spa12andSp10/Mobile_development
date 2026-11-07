package com.example.testcanvas

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.Log
import android.view.MotionEvent
import android.view.View
import java.util.*

class MyView(context: Context?) : View(context) {
    val p = Paint(); val r = Random()
    var layoutWidth = -1; var layoutHeight = -1

    // массив из логических значений  4*4
    val tiles = Array(4) { BooleanArray(4) { r.nextBoolean() } }
    val padding = 10
    lateinit var tilesRect: Array<Array<Rect>>
    var centerX = 50f
    val rect = Rect(0.1f, 0.1f, 0.1f, 0.1f)
    var centerY = 50f
    var w = 1000
    var h = 1000

    private fun isWin(): Boolean = tiles.all {row -> row.all { it }}
    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        super.onLayout(changed, left, top, right, bottom)
        layoutWidth = right - left; layoutHeight = bottom - top
        val cols = tiles.size
        val rows = tiles[0].size

        val cellW = (layoutWidth - padding * (cols + 1)).toFloat() / cols
        val cellH = (layoutHeight - padding * (rows + 1)).toFloat() / rows

        tilesRect = Array(rows) { row ->
            Array(cols) { col ->
                val x = padding + col * (cellW + padding)
                val y = padding + row * (cellH + padding)
                Rect(x, y, cellW, cellH, tiles[row][col])
            }
        }
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        canvas.apply {
            w = width
            h = height

            Log.e("mytag", "onDraw:   canvas ${canvas.width}x${canvas.height}")
            Log.e("mytag", "onLayout: layout $layoutWidth x $layoutHeight")
            if (canvas.width != layoutWidth || canvas.height != layoutHeight) {
                Log.e("mytag", "размеры НЕ совпадают")
            } else {
                Log.e("mytag", "размеры совпадают")
            }

            drawColor(Color.YELLOW)

            if (::tilesRect.isInitialized) {
                for (row in tilesRect) {
                    for (tile in row) {
                        p.color = tile.color
                        drawRect(tile.x, tile.y, tile.x + tile.width, tile.y + tile.height, p)
                    }
                }
            }

            p.color = Color.RED
            drawCircle(centerX, centerY, 30f, p)

            p.color = Color.BLUE
            drawRect(
                rect.x * width,
                rect.y * height,
                (rect.x + rect.width) * width,
                (rect.y + rect.height) * height,
                p
            )

            if (isWin()) {
                p.color = Color.BLACK
                p.textSize = 60f
                drawText("WIN!", width / 2f - 80f, height / 2f, p)
            }
        }
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        event?.apply {
            centerX = x; centerY = y
        }
        invalidate()

        return true
    }
}