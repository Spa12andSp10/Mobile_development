package com.example.peoplelistk

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Adapter
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import kotlin.random.Random
import kotlin.random.nextInt

class MainActivity : AppCompatActivity() {
    lateinit var person: EditText
    lateinit var adapter: ArrayAdapter<String>
    var fullnames = mutableListOf<String>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val lvPeople = findViewById<ListView>(R.id.people)
        val namesList = resources.getStringArray(R.array.samplelist)
        val lastnamesList = resources.getStringArray(R.array.lastnamelist)


        for (i in 0 .. namesList.size)
        {
            fullnames.add("${namesList[Random.nextInt(0, namesList.size)]} ${lastnamesList[Random.nextInt(0, namesList.size)]}")
        }

        val addPerson = findViewById<Button>(R.id.btn)
        person = findViewById<EditText>(R.id.new_people)
        addPerson.setOnClickListener {
            this.onAddPersonClick(addPerson)
        }

        adapter = ArrayAdapter<String>(this, R.layout.item, fullnames)
        lvPeople.adapter = adapter
    }

    fun onAddPersonClick(view: View) {
        val text = person.text.toString()
        fullnames.add(text)
        adapter.notifyDataSetChanged()
    }
}