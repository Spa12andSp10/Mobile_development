package com.example.ugadaychislo

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.content.Intent
import android.view.View
import android.widget.EditText
import com.example.ugadaychislo.GameActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
    fun onGuessClick(view: View) {

        val intent = Intent(this, GameActivity::class.java)
        val value1: EditText = findViewById(R.id.begin)
        val value2: EditText = findViewById(R.id.end)
        val val1 = value1.text.toString().trim()
        val val2 = value2.text.toString().trim()

        val number1: Int? = val1.toIntOrNull()
        val number2: Int? = val2.toIntOrNull()
        intent.putExtra("begin", number1)
        intent.putExtra("end", number2)
        startActivity(intent)

    }
}