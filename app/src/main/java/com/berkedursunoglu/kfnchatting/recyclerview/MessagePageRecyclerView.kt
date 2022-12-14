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

class MessagePageRecyclerView :RecyclerView.Adapter<MessagePageViewHolder>() {

    private val firebaseAuth = FirebaseAuth.getInstance()
    val myData = mutableListOf<MessageModels>()

    fun submitList(newData: ArrayList<MessageModels>) {
        println(" +1 ${myData.toString()}  ")
        myData.clear()
        println("  +2 ${myData.toString()} ")
        myData.addAll(newData)
        println(" +3 ${myData.toString()}  ")
        this.notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MessagePageViewHolder {
        val view = DataBindingUtil.inflate<MessageCardviewRowBinding>(LayoutInflater.from(parent.context),
            R.layout.message_cardview_row,parent,false)
        return MessagePageViewHolder(view)
    }

    override fun onBindViewHolder(holder: MessagePageViewHolder, position: Int) {
        if (myData[position].uid == firebaseAuth.uid){
            holder.binding.messageTextviewMe.text = myData[position].message
            holder.binding.messageTextviewMe.visibility = View.VISIBLE
        }else{
            holder.binding.messageTextviewAgainst.text = myData[position].message
            holder.binding.messageTextviewAgainst.visibility = View.VISIBLE
        }
    }

    override fun getItemCount(): Int {
        return myData.size
    }
}

class MessagePageViewHolder(val binding: MessageCardviewRowBinding) : RecyclerView.ViewHolder(binding.root) {

}
