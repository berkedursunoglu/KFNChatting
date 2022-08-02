package com.berkedursunoglu.kfnchatting.recyclerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.berkedursunoglu.kfnchatting.R
import com.berkedursunoglu.kfnchatting.databinding.AddFriendsCardviewRowBinding
import com.berkedursunoglu.kfnchatting.databinding.RequestFriendsRowBinding
import com.berkedursunoglu.kfnchatting.models.RequestModels
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class RequestRecyclerView(var arrayList:ArrayList<RequestModels>):
    RecyclerView.Adapter<RequestRecyclerViewHolder>() {

    var fireStore= FirebaseFirestore.getInstance()
    var auth =FirebaseAuth.getInstance()

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
        holder.binding.addFriendsActions.setOnClickListener {
            var myFriends = hashMapOf("id" to arrayList[position].id, "uid" to arrayList[position].uid)
            var my = hashMapOf("id" to auth.currentUser?.email.toString(),"uid" to auth.currentUser?.uid.toString())
            auth.currentUser?.let { it1 ->
                fireStore.collection("user").document(it1.uid).collection("myFriends").document(arrayList[position].uid).set(myFriends).addOnSuccessListener {
                    Toast.makeText(holder.itemView.context,"Arkadaş olarak eklendi",Toast.LENGTH_SHORT).show()
                    holder.binding.addFriendsActions.visibility = View.GONE
                    holder.binding.imageviewCheck.visibility = View.VISIBLE
                    fireStore.collection("user").document(it1.uid).collection("requestFriends").document(arrayList[position].id).delete()
                    fireStore.collection("user").document(arrayList[position].uid).collection("myFriends").document(it1.uid).set(my)
                }
            }
        }
    }

    override fun getItemCount(): Int {
       return arrayList.size
    }
}

class RequestRecyclerViewHolder(var binding: RequestFriendsRowBinding) : RecyclerView.ViewHolder(binding.root) {

}
