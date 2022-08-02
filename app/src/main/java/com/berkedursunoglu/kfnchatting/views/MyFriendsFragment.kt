package com.berkedursunoglu.kfnchatting.views

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.berkedursunoglu.kfnchatting.R
import com.berkedursunoglu.kfnchatting.databinding.FragmentMyFriendsBinding
import com.berkedursunoglu.kfnchatting.recyclerview.MyFriendsRecyclerView
import com.berkedursunoglu.kfnchatting.viewmodels.MyFriendsViewModels


class MyFriendsFragment : Fragment() {

    private lateinit var dataBinding:FragmentMyFriendsBinding
    private lateinit var viewModel:MyFriendsViewModels
    private lateinit var myFriendsRecyclerView:MyFriendsRecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        dataBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_my_friends, container, false)
        viewModel = ViewModelProvider(this)[MyFriendsViewModels::class.java]

        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        dataBinding.myFriendsRecyclerview.layoutManager = LinearLayoutManager(requireContext())
        viewModel.getMyFriends()
        viewModel.myFriendsLiveData.observe(viewLifecycleOwner, Observer {
            myFriendsRecyclerView = MyFriendsRecyclerView(it)
            dataBinding.myFriendsRecyclerview.adapter = myFriendsRecyclerView
        })
    }

}