package com.berkedursunoglu.kfnchatting.viewmodels

import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.berkedursunoglu.kfnchatting.models.MyFriendsModel
import com.berkedursunoglu.kfnchatting.repository.FireStoreRepository
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MyFriendsViewModels: ViewModel() {

    var fireStoreRepository: FireStoreRepository = FireStoreRepository()
    var myFriendsLiveData = MutableLiveData<ArrayList<MyFriendsModel>>()

    fun getMyFriends(){
        val myFriendsJob = viewModelScope.launch(Dispatchers.IO){
            var arrayList = ArrayList<MyFriendsModel>()
            fireStoreRepository.getMyFriends().addSnapshotListener { value, error ->
                value.let {
                    it?.documents?.forEach {
                        var id = it.get("id") as String
                        var uid = it.get("uid") as String
                        var user = MyFriendsModel(id,uid)
                        arrayList.add(user)
                    }
                    myFriendsLiveData.value = arrayList
                }
            }
        }
    }
}