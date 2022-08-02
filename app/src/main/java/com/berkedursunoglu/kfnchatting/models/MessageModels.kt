package com.berkedursunoglu.kfnchatting.models

import com.google.firebase.Timestamp

data class MessageModels(var message:String,var uid:String,var timestamp: Timestamp) {
}