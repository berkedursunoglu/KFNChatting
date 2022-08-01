package com.berkedursunoglu.kfnchatting.recyclerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import androidx.lifecycle.get
import androidx.recyclerview.widget.RecyclerView
import com.berkedursunoglu.kfnchatting.R
import com.berkedursunoglu.kfnchatting.`interface`.RecyclerViewCustomClickListener
import com.berkedursunoglu.kfnchatting.databinding.AddFriendsCardviewRowBinding
import com.berkedursunoglu.kfnchatting.models.UserModel
import com.berkedursunoglu.kfnchatting.repository.AddFriendsRepository
import com.berkedursunoglu.kfnchatting.viewmodels.AddFriendsViewModels
import com.berkedursunoglu.kfnchatting.views.AddFriendsFragment

class AddFriendsRecyclerView(var arrayList:ArrayList<UserModel>):
    RecyclerView.Adapter<AddFriendsViewHolder>(){

    private lateinit var addFriendsRepository: AddFriendsRepository

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AddFriendsViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = DataBindingUtil.inflate<AddFriendsCardviewRowBinding>(inflater, R.layout.add_friends_cardview_row,parent,false)
        return AddFriendsViewHolder(view)
    }

    override fun onBindViewHolder(holder: AddFriendsViewHolder, position: Int) {
        holder.binding.variableUser =  arrayList[position]
        holder.binding.moreActionButton.setOnClickListener {
            var popup = PopupMenu(it.context,it)
            popup.inflate(R.menu.recyclerview_menu_item)
            popup.setOnMenuItemClickListener {
                if (it.itemId == R.id.add_friends){
                    addFriendsRepository = AddFriendsRepository()
                    addFriendsRepository.requestFriend(arrayList[position].collectionsID)
                }
                return@setOnMenuItemClickListener true
            }
            popup.gravity = View.TEXT_ALIGNMENT_CENTER
            popup.show()
        }
    }

    override fun getItemCount(): Int {
        return arrayList.size
    }

}

class AddFriendsViewHolder(val binding: AddFriendsCardviewRowBinding) : RecyclerView.ViewHolder(binding.root) {

}
