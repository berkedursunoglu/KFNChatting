package com.berkedursunoglu.kfnchatting.recyclerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.berkedursunoglu.kfnchatting.R
import com.berkedursunoglu.kfnchatting.databinding.AddFriendsCardviewRowBinding
import com.berkedursunoglu.kfnchatting.models.UserModel

class AddFriendsRecyclerView(var arrayList:ArrayList<UserModel>):
    RecyclerView.Adapter<AddFriendsViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AddFriendsViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = DataBindingUtil.inflate<AddFriendsCardviewRowBinding>(inflater, R.layout.add_friends_cardview_row,parent,false)
        return AddFriendsViewHolder(view)
    }

    override fun onBindViewHolder(holder: AddFriendsViewHolder, position: Int) {
        holder.binding.variableUser =  arrayList[position]
    }

    override fun getItemCount(): Int {
        return arrayList.size
    }
}

class AddFriendsViewHolder(val binding: AddFriendsCardviewRowBinding) : RecyclerView.ViewHolder(binding.root) {

}
