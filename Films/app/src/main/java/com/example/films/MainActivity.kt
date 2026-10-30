package com.example.films

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    private lateinit var filmField: TextView
    private lateinit var chooseBtn: Button

    private val films: MutableList<String> by lazy {
        resources.getStringArray(R.array.films).toMutableList()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        applyWindowInsets()

        filmField = findViewById(R.id.filmName)
        chooseBtn = findViewById(R.id.chooseBtn)

        chooseBtn.setOnClickListener { pickFilm() }
    }

    private fun applyWindowInsets() {
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    private fun pickFilm() {
        when {
            films.size > 1 -> {
                val index = Random.nextInt(films.size)
                showFilm(films.removeAt(index))
            }
            films.size == 1 -> showFilm(films.removeAt(0))
            else -> showNoFilmsLeft()
        }
    }

    private fun showFilm(film: String) {
        filmField.text = film
    }

    private fun showNoFilmsLeft() {
        filmField.text = getString(R.string.filmsOver)
        chooseBtn.isEnabled = false
    }
}