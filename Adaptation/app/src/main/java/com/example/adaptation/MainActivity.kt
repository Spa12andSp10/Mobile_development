package com.example.adaptation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Adapter
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.Spinner
import android.widget.Toast
import com.google.android.material.navigation.NavigationBarView.OnItemSelectedListener

class MainActivity : AppCompatActivity(),AdapterView.OnItemSelectedListener {
    lateinit var adapter: ArrayAdapter<CharSequence>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // TODO: заменять картинку на ту, которую установили при нажатии кнопки
        // иначе будет исходная с котиком

        //adapter = ArrayAdapter()
        adapter = ArrayAdapter.createFromResource(this, R.array.pictures, R.layout.item)
        val spinner = findViewById<Spinner>(R.id.pictures_list)
        spinner.adapter = adapter
        spinner.onItemSelectedListener = this
    }
    val cars = intArrayOf(R.drawable.car1, R.drawable.car2, R.drawable.car3)
    private var index = 0
    fun onChangePictureClick(v: View) {
        val iv = findViewById<ImageView>(R.id.picture)

        index = (index + 1) % cars.size
        iv.setImageResource(cars[index])
    }

    private var firstStart = true

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        if(firstStart)
        {
            firstStart = false
            return
        }
        Toast.makeText(this, "выбран элемент $position", Toast.LENGTH_SHORT ).show()
        val iv = findViewById<ImageView>(R.id.picture)
        iv.setImageResource(cars[position])
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
        Toast.makeText(this, "не выбран элемент", Toast.LENGTH_SHORT ).show()
    }
}