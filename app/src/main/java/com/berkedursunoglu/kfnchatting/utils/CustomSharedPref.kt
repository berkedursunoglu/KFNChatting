package com.berkedursunoglu.kfnchatting.utils

import android.content.Context
import android.content.SharedPreferences

abstract class CustomSharedPref {

    private var sharedPref:SharedPreferences? = null

    fun getShared(context: Context):SharedPreferences?{
        return sharedPref ?: createShared(context)
    }

    fun getCollectionsID(sharedTag:String):String?{
        return sharedPref?.getString(sharedTag,"null")
    }

    private fun createShared(context: Context):SharedPreferences?{
        return context.getSharedPreferences("userPref",Context.MODE_PRIVATE).also {
            sharedPref = it
        }
    }
}