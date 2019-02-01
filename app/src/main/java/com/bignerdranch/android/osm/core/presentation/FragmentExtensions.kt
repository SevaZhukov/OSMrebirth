package com.bignerdranch.android.osm.core.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.google.gson.Gson
import java.lang.reflect.Type

fun Fragment.putObject(key: String, data: Any) {
    val gson = Gson()
    val json = gson.toJson(data, data.javaClass)
    val bundle = Bundle()
    bundle.putString(key, json)
    this.arguments = bundle
}

fun Any.createBundle(key: String, data: Any): Bundle {
    val gson = Gson()
    val json = gson.toJson(data, data.javaClass)
    val bundle = Bundle()
    bundle.putString(key, json)
    return bundle
}

fun Fragment.getFromBundle(key: String, type: Type): Any {
    val gson = Gson()
    val json = arguments!!.getString(key)
    return gson.fromJson(json, type)
}