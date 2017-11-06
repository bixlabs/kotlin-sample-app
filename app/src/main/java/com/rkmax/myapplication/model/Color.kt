package com.rkmax.myapplication.model

import com.github.kittinunf.fuel.core.ResponseDeserializable
import com.google.gson.Gson

data class Color(
        private val name: Value = Value()
) {
    class Deserializer : ResponseDeserializable<Color> {
        override fun deserialize(content: String) : Color? = Gson().fromJson(content, Color::class.java)
    }

    override fun toString() = name.value
}