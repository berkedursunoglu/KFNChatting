package com.berkedursunoglu.kfnchatting.recyclerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.berkedursunoglu.kfnchatting.R
import com.berkedursunoglu.kfnchatting.databinding.MessageCardviewRowBinding

class MessagePageRecyclerView(val arrayList:ArrayList<Any>):RecyclerView.Adapter<MessagePageViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MessagePageViewHolder {
        val view = DataBindingUtil.inflate<MessageCardviewRowBinding>(LayoutInflater.from(parent.context),
            R.layout.message_cardview_row,parent,false)
        return MessagePageViewHolder(view)
    }

    override fun onBindViewHolder(holder: MessagePageViewHolder, position: Int) {

    }

    override fun getItemCount(): Int {
        return arrayList.size
    }
}

class MessagePageViewHolder(val binding: MessageCardviewRowBinding) : RecyclerView.ViewHolder(binding.root) {

}
