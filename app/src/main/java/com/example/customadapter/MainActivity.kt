package com.example.customadapter

import android.content.Context
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.Spinner
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val spinner = findViewById<Spinner>(R.id.spinner)
        val canvas = findViewById<View>(R.id.colorDisplay)

        val colors = resources.getStringArray(R.array.color_array)

        spinner.adapter = ColorAdapter(this, colors)
    }
}

// Create the adapter class outside of main

// We need to add a constructor after ColorAdapter to link the class to the MainActivity
class ColorAdapter(_context: Context, _colors : Array<String>) : BaseAdapter(){
    private val context = _context
    private val colors = _colors

    override fun getCount(): Int {
        return  colors.size
    }

    override fun getItem(p0: Int): Any {
        return colors[p0]
    }

    override fun getItemId(p0: Int): Long {
        return p0.toLong()
    }

    override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {

        //val textView = TextView(context)

        val textView : TextView

        if (p1 != null){
            // This is a previously created TextView
            // cast it
            textView = p1 as TextView
        }
        else{
            textView = TextView(context)
            textView.textSize = 22f // 22f means its a float value
            textView.setPadding(5,10,0,10)
            if (p0 == 0){
                textView.setBackgroundColor(Color.WHITE)
            }
            else{
                textView.setBackgroundColor(Color.parseColor(colors[p0]))
            }
        }
        textView.text = colors[p0]

        TODO()
        // Make some sort of a listener to pass the color read in the array to outside of the
        // spinner, so the background color can be read

        return textView
    }
}