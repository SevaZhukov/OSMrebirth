package com.memebattle.template.features.auth.core.domain.interactor

import android.content.SharedPreferences

class AuthSettingsService(var sharedPreferences: SharedPreferences) {
    val ID = "id"

    val ERROR = "error"

    fun setId(id: String) {
        val editor = sharedPreferences.edit()
        editor.putString(ID, id)
        editor.apply()
    }

    fun getId(): String {
        return sharedPreferences.getString(ID, ERROR)!!
    }
}