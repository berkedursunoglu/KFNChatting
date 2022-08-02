package com.berkedursunoglu.kfnchatting.recyclerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.berkedursunoglu.kfnchatting.R
import com.berkedursunoglu.kfnchatting.databinding.MyFriendsRowBinding
import com.berkedursunoglu.kfnchatting.models.MyFriendsModel
import com.berkedursunoglu.kfnchatting.views.MyFriendsFragmentDirections

class MyFriendsRecyclerView(var arrayList:ArrayList<MyFriendsModel>):
    RecyclerView.Adapter<MyFriendsViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyFriendsViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val databinding = DataBindingUtil.inflate<MyFriendsRowBinding>(inflater, R.layout.my_friends_row,parent,false)
        return MyFriendsViewHolder(databinding)
    }

    override fun onBindViewHolder(holder: MyFriendsViewHolder, position: Int) {
        holder.binding.myFriendsModel = arrayList[position]
        holder.binding.messageThrow.setOnClickListener {
            val action = MyFriendsFragmentDirections.actionMyFriendsFragmentToMessagePage(arrayList[position].id,arrayList[position].uid)
            it.findNavController().navigate(action)
        }
    }

    override fun getItemCount(): Int {
        return arrayList.size
    }
}

class MyFriendsViewHolder(var binding: MyFriendsRowBinding) : RecyclerView.ViewHolder(binding.root) {

}
