package com.example.mykotlin

import android.content.Context
import android.content.SharedPreferences
import kotlin.reflect.KProperty



class UserPreferences(context: Context) {
    private val sharedPreferences = context.getSharedPreferences("UserPrefs", Context.MODE_PRIVATE)

    var username: String by SharedPreferencesDelegate(sharedPreferences, "username", "Guest")
}


class SharedPreferencesDelegate(
    private val prefs: SharedPreferences,
    private val key: String,
    private val defaultValue: String
) {
    operator fun getValue(thisRef: Any?, property: KProperty<*>): String {
        return prefs.getString(key, defaultValue) ?: defaultValue
    }

    operator fun setValue(thisRef: Any?, property: KProperty<*>, value: String) {
        prefs.edit().putString(key, value).apply()
    }
}
