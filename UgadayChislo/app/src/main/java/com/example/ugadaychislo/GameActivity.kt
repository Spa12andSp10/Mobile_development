package com.example.ugadaychislo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView

class GameActivity : AppCompatActivity() {
    var begin: Int = 0
    var end: Int = 100

    // флаг: игра окончена — кнопки больше не должны менять состояние
    var gameOver: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)
        begin = intent.getIntExtra("begin", 0)
        end = intent.getIntExtra("end", 100)
        Log.d("mytag", "begin = $begin")
        Log.d("mytag", "end = $end")
        showQuestion()
    }

    private fun showQuestion() {
        val tvQuestion = findViewById<TextView>(R.id.question)
        if (end - begin <= 1) {
            tvQuestion.text = "Ваше число $begin?"
        } else {
            val middle = (begin + end) / 2
            tvQuestion.text = "Ваше число больше $middle?"
        }
    }

    fun onYesNoClick(view: View) {
        if (gameOver) return

        if (end - begin <= 1) {
            gameOver = true
            val tvQuestion = findViewById<TextView>(R.id.question)
            when (view.id) {
                R.id.yes -> tvQuestion.text = "Угадал!"
                R.id.no  -> tvQuestion.text = "Не угадал."
            }
            return
        }
        
        val middle = (begin + end) / 2
        when (view.id) {
            R.id.yes -> begin = middle + 1
            R.id.no  -> end = middle
        }
        showQuestion()
    }
}