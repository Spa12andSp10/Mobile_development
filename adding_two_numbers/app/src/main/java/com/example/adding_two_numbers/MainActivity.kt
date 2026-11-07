package com.example.adding_two_numbers

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun onClick(v: View) {
        val etA = findViewById<EditText>(R.id.numA)
        val etB = findViewById<EditText>(R.id.numB)
        val tvSum = findViewById<TextView>(R.id.sum)

        val strA = etA.text.toString()
        val strB = etB.text.toString()

        if (strA.isEmpty() || strB.isEmpty() || strA == "." || strB == ".")
        {
            Toast.makeText(this, "Заполните оба поля", Toast.LENGTH_SHORT).show()
            return
        }

        val sum = strA.toDouble() + strB.toDouble()
        val strSum = sum.toString()
        tvSum.text = strSum
    }
}