package com.example.customadapter

import android.content.Context
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
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

        var selected = false

        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {

            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                if (p2 == 0){
                    canvas.setBackgroundColor(Color.WHITE)
                    spinner.setBackgroundColor(Color.WHITE)
                }
                else{
                    // Set background color of layout to selected color
                    canvas.setBackgroundColor(Color.parseColor(colors[p2]))
                    spinner.setBackgroundColor(Color.WHITE)
                    selected = true
                }
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
            }

        }

        spinner.adapter = ColorAdapter(this, colors, selected)

    }
}

// Create the adapter class outside of main

// We need to add a constructor after ColorAdapter to link the class to the MainActivity
class ColorAdapter(_context: Context, _colors : Array<String>, _selected: Boolean) : BaseAdapter(){
    private val context = _context
    private val colors = _colors
    private var selected = _selected

    override fun getCount(): Int {
        return colors.size
    }

    override fun getItem(p0: Int): Any {
        return colors[p0]
    }

    override fun getItemId(p0: Int): Long {
        return p0.toLong()
    }

    override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {

        val textView = (p1 as? TextView) ?: TextView(context).apply {
            textSize = 22f
            setPadding(5, 10, 0, 10)
        }
        textView.text = colors[p0]

//        if (p0 != 0 && selected) {
//            // Set background color to match the text
//            textView.setBackgroundColor(Color.parseColor(colors[p0]))
//        }
//        else {
//            // Set background color to white
//            textView.setBackgroundColor(Color.WHITE)
//            selected = true
//        }

        return textView

    }

    override fun getDropDownView(p0: Int, p1: View?, p2: ViewGroup?): View {
        val textView = (p1 as? TextView) ?: TextView(context).apply {
            textSize = 22f
            setPadding(5, 10, 0, 10)
        }
        textView.text = colors[p0]

        if (p0 != 0) {
            // Set background color to match the text
            textView.setBackgroundColor(Color.parseColor(colors[p0]))
            textView.setTextColor(Color.BLACK)
        } else {
            // Set background color to white and text color to black
            textView.setBackgroundColor(Color.WHITE)
            textView.setTextColor(Color.BLACK)
        }

        return textView
    }


}