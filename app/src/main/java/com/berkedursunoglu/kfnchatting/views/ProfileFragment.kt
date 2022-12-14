package com.berkedursunoglu.kfnchatting.views

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.berkedursunoglu.kfnchatting.R
import com.berkedursunoglu.kfnchatting.databinding.FragmentProfileBinding
import com.google.firebase.auth.FirebaseAuth


class ProfileFragment : Fragment() {

    private lateinit var dataBinding:FragmentProfileBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        dataBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_profile,container,false)
        dataBinding.functions = this
        return dataBinding.root
    }

    fun signOut(){
        val firebaseAuth = FirebaseAuth.getInstance()
        firebaseAuth.signOut()
        startActivity(Intent(requireActivity() ,LoginActivity::class.java))
        requireActivity().finish()
    }

    fun gotoRequestFragment(){
        val action = ProfileFragmentDirections.actionProfileFragmentToRequestFriendsFragment()
        view?.findNavController()?.navigate(action)
    }

    fun gotoMyFriendsFragment(){
        val action = ProfileFragmentDirections.actionProfileFragmentToMyFriendsFragment()
        view?.findNavController()?.navigate(action)
    }
}