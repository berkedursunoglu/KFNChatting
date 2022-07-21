package com.berkedursunoglu.kfnchatting.utils

import android.app.Application
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity

class Utils {

    companion object {

        fun edittextNullCheckThreeVar(
            stringOne: String,
            stringTwo: String,
            stringThree: String,
        ): Boolean {
            if (stringOne == "" || stringTwo == "" || stringThree == "") {
                return true
            }
            return false
        }

        fun edittextNullCheckTwoVar(
            stringOne: String,
            stringTwo: String,
        ): Boolean {
            if (stringOne == "" || stringTwo == "") {
                return true
            }
            return false
        }
    }



}