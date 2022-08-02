package com.berkedursunoglu.kfnchatting.viewmodels

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.berkedursunoglu.kfnchatting.models.UserModel
import com.berkedursunoglu.kfnchatting.repository.AddFriendsRepository
import com.berkedursunoglu.kfnchatting.repository.FireStoreRepository
import com.google.firebase.Timestamp
import com.google.firebase.firestore.auth.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AddFriendsViewModels: ViewModel() {

    private val addFriendsRepository= AddFriendsRepository()
    var addUser = MutableLiveData<ArrayList<UserModel>>()
    var addUserError = MutableLiveData<String>()
    var addUserResponse = MutableLiveData<Boolean>()

    fun getUsers(){
        val addJob = viewModelScope.launch(Dispatchers.IO) {
            addFriendsRepository.getUsers().addSnapshotListener { value, error ->
                if (value !=null){
                    var arrayList = ArrayList<UserModel>()

                    value.documents.forEach {
                        val username = it.get("id") as String
                        val time = it.get("time") as Timestamp
                        val uid = it.get("uid") as String
                        val user = UserModel(username,time,uid)
                        arrayList.add(user)
                    }
                    addUser.value = arrayList
                }
                if (error != null){
                    addUserError.value = error.localizedMessage
                }
            }
        }
    }

    fun addFriends(collectionsID:String){
        val addJOB = viewModelScope.launch(Dispatchers.IO) {
            addFriendsRepository.requestFriend(collectionsID).addOnSuccessListener {
                addUserResponse.value = true
            }
        }
    }


}