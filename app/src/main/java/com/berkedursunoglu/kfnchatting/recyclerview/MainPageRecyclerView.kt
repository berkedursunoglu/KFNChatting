package com.berkedursunoglu.kfnchatting.recyclerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.berkedursunoglu.kfnchatting.R
import com.berkedursunoglu.kfnchatting.databinding.MainpageCardviewBinding

class MainPageRecyclerview(var arrayList:ArrayList<Any>):RecyclerView.Adapter<MainPageViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainPageViewHolder {
        val dataBinding = DataBindingUtil.inflate<MainpageCardviewBinding>(LayoutInflater.from(parent.context),
            R.layout.mainpage_cardview,parent,false)
        return MainPageViewHolder(dataBinding)
    }

    override fun onBindViewHolder(holder: MainPageViewHolder, position: Int) {
    }

    override fun getItemCount(): Int {
        return arrayList.size
    }
}

class MainPageViewHolder(val binding: MainpageCardviewBinding ) : RecyclerView.ViewHolder(binding.root) {

}
