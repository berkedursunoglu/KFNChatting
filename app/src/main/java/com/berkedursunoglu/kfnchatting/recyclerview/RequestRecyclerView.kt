package com.berkedursunoglu.kfnchatting.recyclerview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.berkedursunoglu.kfnchatting.R
import com.berkedursunoglu.kfnchatting.databinding.AddFriendsCardviewRowBinding
import com.berkedursunoglu.kfnchatting.databinding.RequestFriendsRowBinding
import com.berkedursunoglu.kfnchatting.models.RequestModels

class RequestRecyclerView(var arrayList:ArrayList<RequestModels>):
    RecyclerView.Adapter<RequestRecyclerViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RequestRecyclerViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = DataBindingUtil.inflate<RequestFriendsRowBinding>(inflater, R.layout.request_friends_row,parent,false)
        return RequestRecyclerViewHolder(view)
    }

    override fun onBindViewHolder(holder: RequestRecyclerViewHolder, position: Int) {
        holder.binding.variables = arrayList[position]
        arrayList.forEach {
            println(it.id)
            println(it.uid)
            println(it.apply.toString())
            println(it.timestamp.toString())
        }
    }

    override fun getItemCount(): Int {
       return arrayList.size
    }
}

class RequestRecyclerViewHolder(var binding: RequestFriendsRowBinding) : RecyclerView.ViewHolder(binding.root) {

}
