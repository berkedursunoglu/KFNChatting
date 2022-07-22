package com.berkedursunoglu.kfnchatting.models

import com.google.firebase.Timestamp

data class UserModel(
    var userUID:String,
    var userTimestamp: Timestamp
) {
}