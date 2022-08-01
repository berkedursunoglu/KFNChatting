package com.berkedursunoglu.kfnchatting.viewmodels

import android.annotation.SuppressLint
import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.berkedursunoglu.kfnchatting.repository.AuthRepository
import com.berkedursunoglu.kfnchatting.repository.FireStoreRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RegisterFragmentViewModels: ViewModel() {

    var registerBoolean = MutableLiveData<Boolean>()
    var registerLogMessage = MutableLiveData<String>()
    private val repository:AuthRepository = AuthRepository()
    @SuppressLint("StaticFieldLeak")
    private val fireStore = FireStoreRepository()

    fun registerLogin(email:String,password:String,context:Context){
        val jobRegister = viewModelScope.launch(Dispatchers.IO) {
            repository.registerUser(email,password).addOnSuccessListener {
                if (it.user != null){
                    fireStore.addUser(it.user!!.email!!,it.user!!.uid,context)
                }
                registerBoolean.value = true
            }.addOnFailureListener {
                registerLogMessage.value = it.localizedMessage?.toString()
            }
        }
    }
}