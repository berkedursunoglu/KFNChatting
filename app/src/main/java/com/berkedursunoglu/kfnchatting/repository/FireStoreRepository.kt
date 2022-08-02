package com.berkedursunoglu.kfnchatting.repository

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import com.berkedursunoglu.kfnchatting.utils.CustomSharedPref
import com.google.firebase.Timestamp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore

class FireStoreRepository: CustomSharedPref() {

    val fireStore = FirebaseFirestore.getInstance()
    val fireAuth = FirebaseAuth.getInstance()

    fun addUser(id:String,uid:String,context:Context){
        val user = hashMapOf("id" to id,"time" to Timestamp.now(),"uid" to uid)
        fireStore.collection("user").document(uid).set(user).addOnSuccessListener {
            var shared = getShared(context)
            var edit = shared?.edit()
            edit?.putString("userUID",uid)
            edit?.commit()
        }
    }

    fun getRequestFriendList(context: Context):CollectionReference{
        var shared = getShared(context)
        var string = shared?.getString("userUID","null")
        println(string)
        return fireStore.collection("user").document(string.toString()).collection("requestFriends")
    }

    fun getMyFriends():CollectionReference{
        var userUID = fireAuth.uid
        return fireStore.collection("user").document(userUID.toString()).collection("myFriends")
    }


}