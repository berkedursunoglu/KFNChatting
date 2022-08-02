package com.berkedursunoglu.kfnchatting.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.berkedursunoglu.kfnchatting.models.MessageModels
import com.berkedursunoglu.kfnchatting.repository.MessageRepository
import com.google.firebase.Timestamp
import com.google.firebase.firestore.Query
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SendMessageViewModels: ViewModel() {

    var messageRepository = MessageRepository()
    var messageLiveData = MutableLiveData<ArrayList<MessageModels>>()

    fun sendMessage(anotherUserID:String,message:String){
        var sendJOB = viewModelScope.launch(Dispatchers.IO) {
            messageRepository.messageSend(anotherUserID,message)
        }
    }

    fun getMessage(anotherUserID:String){
        var arrayList = ArrayList<MessageModels>()
        var getJOB = viewModelScope.launch(Dispatchers.IO) {
            messageRepository.getMessage(anotherUserID).orderBy("time",Query.Direction.ASCENDING).addSnapshotListener { value, error ->
                arrayList.clear()
                value?.documents?.forEach {
                    var message = it.get("message") as String
                    var uid = it.get("uid") as String
                    var time = it.get("time") as Timestamp
                    var messageModel = MessageModels(message,uid,time)
                    arrayList.add(messageModel)
                }
                messageLiveData.value = arrayList
            }
        }
    }
}