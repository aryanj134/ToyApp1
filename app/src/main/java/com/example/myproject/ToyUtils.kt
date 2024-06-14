package com.example.myproject

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

object ToyUtils {
    private val gson = Gson()

    fun fromListToJson(toys: List<Toys>): String {
        return gson.toJson(toys)
    }

    fun fromJsonToList(json: String): MutableList<Toys> {
        val listType = object : TypeToken<MutableList<Toys>>() {}.type
        return gson.fromJson(json, listType)
    }
}