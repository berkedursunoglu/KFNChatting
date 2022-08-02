package com.berkedursunoglu.kfnchatting.models

import com.google.firebase.Timestamp

data class UserModel(
    var userID:String,
    var userTimestamp: Timestamp,
    var userUID:String
) {
}