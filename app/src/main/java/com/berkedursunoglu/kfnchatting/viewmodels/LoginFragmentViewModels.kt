package com.berkedursunoglu.kfnchatting.viewmodels

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.berkedursunoglu.kfnchatting.repository.AuthRepository
import com.berkedursunoglu.kfnchatting.repository.FireStoreRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LoginFragmentViewModels: ViewModel() {

    private val firebaseAuth = AuthRepository()
    var loginLogMessage = MutableLiveData<String>()
    var loginBoolean = MutableLiveData<Boolean>()

    fun loginUser(email:String,password:String,context: Context){
        val loginJob = viewModelScope.launch(Dispatchers.IO) {
            firebaseAuth.loginUser(email,password).addOnSuccessListener {
                loginBoolean.value = true
                var shared = context.getSharedPreferences("userPref",Context.MODE_PRIVATE)
                var edit = shared.edit()
                edit.putString("userUID",it.user?.uid)
                edit.commit()
            }.addOnFailureListener {
                loginLogMessage.value = it.localizedMessage?.toString()
            }
        }
    }


}