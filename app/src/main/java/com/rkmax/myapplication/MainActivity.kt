package com.rkmax.myapplication

import android.graphics.Color
import android.graphics.PorterDuff
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.github.kittinunf.fuel.httpGet
import com.madrapps.pikolo.listeners.SimpleColorSelectionListener

import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private var color = Color.RED

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        colorPicker.setColorSelectionListener(object : SimpleColorSelectionListener() {
            override fun onColorSelected(color: Int) {
                setColor(color)
                updateUI(color)
            }
        })

        pickedColor.setOnClickListener { findColorName() }
        pickedColorText.setOnClickListener { findColorName() }

        colorPicker.setColor(color)
        updateUI(color)
    }

    fun setColor(c: Int) {
        color = c
    }

    fun updateUI(color: Int) {
        pickedColor.background.setColorFilter(color, PorterDuff.Mode.MULTIPLY)
        pickedColorText.text = "#${getHexColor(color)}"
    }

    private fun findColorName() {
        pickedColorName.text = ""
        "http://www.thecolorapi.com/id?format=json&hex=${getHexColor(color)}".httpGet()
                .responseObject(com.rkmax.myapplication.model.Color.Deserializer()) { _, _, result ->
                    val (color) = result
                    pickedColorName.text = color?.toString()
                }
    }

    private fun getHexColor(color: Int) = String.format("%06X", 0xFFFFFF and color)
}






