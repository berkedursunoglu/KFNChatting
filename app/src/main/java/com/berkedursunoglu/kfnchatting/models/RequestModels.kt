package com.berkedursunoglu.kfnchatting.models

import com.google.firebase.Timestamp

data class RequestModels(var apply:Boolean,var id:String,var timestamp: Timestamp,var uid:String) {
}