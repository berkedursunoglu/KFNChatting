package com.berkedursunoglu.kfnchatting.repository

import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ListenerRegistration
import com.google.firebase.firestore.QuerySnapshot

class AddFriendsRepository {

    val firebaseAuth = FirebaseAuth.getInstance()
    val firebaseStore = FirebaseFirestore.getInstance()

    fun getUserInfo(){

    }

    suspend fun getUsers():CollectionReference{
        return firebaseStore.collection("user")
    }



}