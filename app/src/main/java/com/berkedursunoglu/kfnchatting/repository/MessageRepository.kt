package com.berkedursunoglu.kfnchatting.repository

import com.google.firebase.Timestamp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore

class MessageRepository {

    private val fireAuth = FirebaseAuth.getInstance()
    private val firebaseFirestore = FirebaseFirestore.getInstance()


    suspend fun messageSend(anotherUserUID:String,message:String){
        var messages = hashMapOf("message" to message, "uid" to fireAuth.uid,"time" to Timestamp.now())
        firebaseFirestore.collection("user").document(fireAuth.uid.toString()).collection("messages").document("${fireAuth.uid}${anotherUserUID}").collection("${fireAuth.uid}${anotherUserUID}").add(messages)
        firebaseFirestore.collection("user").document(anotherUserUID).collection("messages").document("${anotherUserUID}${fireAuth.uid}").collection("${anotherUserUID}${fireAuth.uid}").add(messages)
    }

    suspend fun getMessage(anotherUserUID:String): CollectionReference {
        return firebaseFirestore.collection("user").document(fireAuth.uid.toString()).collection("messages").document("${fireAuth.uid}${anotherUserUID}").collection("${fireAuth.uid}${anotherUserUID}")
    }
}