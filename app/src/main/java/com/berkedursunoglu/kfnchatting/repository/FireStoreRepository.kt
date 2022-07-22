package com.berkedursunoglu.kfnchatting.repository

import com.google.firebase.Timestamp
import com.google.firebase.firestore.FirebaseFirestore

class FireStoreRepository {

    val fireStore = FirebaseFirestore.getInstance()

    fun addUser(uid:String){
        val user = hashMapOf("uid" to uid,"time" to Timestamp.now())
        fireStore.collection("user").add(user)
    }

}