package com.berkedursunoglu.kfnchatting.recyclerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.berkedursunoglu.kfnchatting.R
import com.berkedursunoglu.kfnchatting.databinding.MessageCardviewRowBinding
import com.berkedursunoglu.kfnchatting.models.MessageModels
import com.google.firebase.auth.FirebaseAuth

class MessagePageRecyclerView(val arrayList:ArrayList<MessageModels>):RecyclerView.Adapter<MessagePageViewHolder>() {

    private val firebaseAuth = FirebaseAuth.getInstance()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MessagePageViewHolder {
        val view = DataBindingUtil.inflate<MessageCardviewRowBinding>(LayoutInflater.from(parent.context),
            R.layout.message_cardview_row,parent,false)
        return MessagePageViewHolder(view)
    }

    override fun onBindViewHolder(holder: MessagePageViewHolder, position: Int) {
        if (arrayList[position].uid == firebaseAuth.uid){
            holder.binding.messageTextviewMe.text = arrayList[position].message
            holder.binding.messageTextviewMe.visibility = View.VISIBLE
        }else{
            holder.binding.messageTextviewAgainst.text = arrayList[position].message
            holder.binding.messageTextviewAgainst.visibility = View.VISIBLE
        }
    }

    override fun getItemCount(): Int {
        return arrayList.size
    }
}

class MessagePageViewHolder(val binding: MessageCardviewRowBinding) : RecyclerView.ViewHolder(binding.root) {

}
