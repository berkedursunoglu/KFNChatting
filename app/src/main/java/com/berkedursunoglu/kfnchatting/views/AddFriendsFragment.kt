package com.berkedursunoglu.kfnchatting.views

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.berkedursunoglu.kfnchatting.R
import com.berkedursunoglu.kfnchatting.databinding.FragmentAddFriendsBinding
import com.berkedursunoglu.kfnchatting.models.UserModel
import com.berkedursunoglu.kfnchatting.recyclerview.AddFriendsRecyclerView
import com.berkedursunoglu.kfnchatting.viewmodels.AddFriendsViewModels
import com.google.firebase.auth.FirebaseAuth


class AddFriendsFragment : Fragment() {

    private lateinit var dataBinding: FragmentAddFriendsBinding
    private lateinit var viewModel: AddFriendsViewModels
    private lateinit var recyclerView: AddFriendsRecyclerView
    private var checkUserList: ArrayList<UserModel> = ArrayList<UserModel>()
    private val user = FirebaseAuth.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        dataBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_add_friends, container, false)
        viewModel = ViewModelProvider(this)[AddFriendsViewModels::class.java]
        dataBinding.addFriendsRecyclerview.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel.getUsers()
        viewModel.addUser.observe(viewLifecycleOwner, Observer {
            checkUserList.clear()
            it.forEach {
                if (user.currentUser?.uid != it.userUID) {
                    var userModel =
                        UserModel(it.userID, it.userTimestamp, it.userUID)
                    checkUserList.add(userModel)
                }
            }
            recyclerView = AddFriendsRecyclerView(checkUserList)
            dataBinding.addFriendsRecyclerview.adapter = recyclerView
        })
        viewModel.addUserError.observe(viewLifecycleOwner, Observer {
            Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
            Log.d("AddFrinedsFragment", it)
        })
    }

}