package com.berkedursunoglu.kfnchatting.viewmodels

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.berkedursunoglu.kfnchatting.models.RequestModels
import com.berkedursunoglu.kfnchatting.repository.FireStoreRepository
import com.google.firebase.Timestamp
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RequestFriendsViewModels: ViewModel() {

    private var repository:FireStoreRepository = FireStoreRepository()
    private var arrayList = ArrayList<RequestModels>()
    var requestList = MutableLiveData<ArrayList<RequestModels>>()

    fun getRequestFriends(context: Context){
        viewModelScope.launch(Dispatchers.IO) {
            repository.getRequestFriendList(context).addSnapshotListener { value, error ->
                value.let {
                    value?.documents?.forEach {
                        var apply = it.get("apply") as Boolean
                        var id = it.get("id") as String
                        var timeStap = it.get("timestap") as Timestamp
                        var uid = it.get("uid") as String
                        var user = RequestModels(apply,id,timeStap,uid)
                        arrayList.add(user)
                    }
                    requestList.value = arrayList
                    println(arrayList.size)
                }
            }
        }
    }

}