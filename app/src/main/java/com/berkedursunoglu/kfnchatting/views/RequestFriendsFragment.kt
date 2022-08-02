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
import com.berkedursunoglu.kfnchatting.databinding.FragmentRequestFriendsBinding
import com.berkedursunoglu.kfnchatting.models.RequestModels
import com.berkedursunoglu.kfnchatting.recyclerview.RequestRecyclerView
import com.berkedursunoglu.kfnchatting.viewmodels.RequestFriendsViewModels


class RequestFriendsFragment : Fragment() {

    private lateinit var dataBinding: FragmentRequestFriendsBinding
    private lateinit var viewModel: RequestFriendsViewModels
    private lateinit var requestFriendsRecyclerView: RequestRecyclerView
    private var requestFriendsList = ArrayList<RequestModels>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        dataBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_request_friends, container, false)
        viewModel = ViewModelProvider(this)[RequestFriendsViewModels::class.java]
        dataBinding.requestRecyclerView.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        requestFriendsRecyclerView = RequestRecyclerView()
        dataBinding.requestRecyclerView.adapter = requestFriendsRecyclerView

        viewModel.getRequestFriends(requireContext())
        viewModel.requestList.observe(viewLifecycleOwner, Observer {
            requestFriendsRecyclerView.submitList(it)
        })
    }


}