package com.memebattle.template.core.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.google.gson.Gson

fun Fragment.putObject(key: String, data: Any) {
    val gson = Gson()
    val json = gson.toJson(data, data.javaClass)
    val bundle = Bundle()
    bundle.putString(key, json)
    this.arguments = bundle
}