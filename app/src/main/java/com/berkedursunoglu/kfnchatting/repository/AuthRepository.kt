package com.berkedursunoglu.kfnchatting.repository

import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth

class AuthRepository {

    private val firebaseAuth:FirebaseAuth = FirebaseAuth.getInstance()

    suspend fun registerUser(mail:String,password:String):Task<AuthResult>{
        return firebaseAuth.createUserWithEmailAndPassword(mail,password)
    }

    suspend fun loginUser(mail:String,password: String):Task<AuthResult>{
        return firebaseAuth.signInWithEmailAndPassword(mail,password)
    }
}