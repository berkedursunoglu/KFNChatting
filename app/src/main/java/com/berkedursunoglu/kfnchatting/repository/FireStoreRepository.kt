package com.berkedursunoglu.kfnchatting.repository

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import com.berkedursunoglu.kfnchatting.utils.CustomSharedPref
import com.google.firebase.Timestamp
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore

class FireStoreRepository: CustomSharedPref() {

    val fireStore = FirebaseFirestore.getInstance()

    fun addUser(id:String,uid:String,context:Context){
        val user = hashMapOf("id" to id,"time" to Timestamp.now(),"uid" to uid)
        fireStore.collection("user").add(user).addOnSuccessListener {
            val userUpdate = hashMapOf("id" to id,"time" to Timestamp.now(),"uid" to uid,"collectionsID" to it.id)
            fireStore.collection("user").document(it.id).set(userUpdate)
            var shared = getShared(context)
            var edit = shared?.edit()
            edit?.putString("userCollectionsID",it.id)
            edit?.commit()
        }
    }

    fun getRequestFriendList(context: Context):CollectionReference{
        var shared = getShared(context)
        var string = shared?.getString("userCollectionsID","null")
        println(string)
        return fireStore.collection("user").document(string.toString()).collection("requestFriends")
    }

}