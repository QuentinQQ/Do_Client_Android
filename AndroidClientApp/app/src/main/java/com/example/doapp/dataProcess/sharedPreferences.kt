package com.example.doapp.dataProcess

import android.content.Context


fun saveUserId(context: Context, userId: String) {
    val sharedPreferences = context.getSharedPreferences("AppPrefs", Context.MODE_PRIVATE)
    sharedPreferences.edit().putString("userId", userId).apply()
}


fun getUserId(context: Context): String?{
    val sharedPreferences = context.getSharedPreferences("AppPrefs", Context.MODE_PRIVATE)
    return sharedPreferences.getString("userId", null)
}
