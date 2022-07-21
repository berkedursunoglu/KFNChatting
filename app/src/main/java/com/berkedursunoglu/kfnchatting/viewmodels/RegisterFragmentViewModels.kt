package com.berkedursunoglu.kfnchatting.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.berkedursunoglu.kfnchatting.repository.AuthRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RegisterFragmentViewModels: ViewModel() {

    var registerBoolean = MutableLiveData<Boolean>()
    var registerLogMessage = MutableLiveData<String>()
    private val repository:AuthRepository = AuthRepository()

    fun registerLogin(email:String,password:String){
        val jobRegister = viewModelScope.launch(Dispatchers.IO) {
            repository.registerUser(email,password).addOnSuccessListener {
                registerBoolean.value = true
            }.addOnFailureListener {
                registerLogMessage.value = it.localizedMessage?.toString()
            }
        }
    }
}