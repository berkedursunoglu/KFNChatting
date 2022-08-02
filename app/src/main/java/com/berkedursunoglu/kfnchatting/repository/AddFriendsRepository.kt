package com.berkedursunoglu.kfnchatting.repository

import com.google.android.gms.tasks.Task
import com.google.firebase.Timestamp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.*

class AddFriendsRepository {

    val firebaseAuth = FirebaseAuth.getInstance()
    val firebaseStore = FirebaseFirestore.getInstance()


    suspend fun getUsers(): CollectionReference {
        return firebaseStore.collection("user")
    }

    fun requestFriend(userUID: String): Task<Void> {
        var user = hashMapOf("id" to firebaseAuth.currentUser?.email,
            "timestap" to Timestamp.now(),
            "uid" to firebaseAuth.currentUser?.uid,
            "apply" to false)
        var request =
            firebaseStore.collection("user").document(userUID).collection("requestFriends").document(firebaseAuth.currentUser!!.email!!).set(user)
        return request
    }

}