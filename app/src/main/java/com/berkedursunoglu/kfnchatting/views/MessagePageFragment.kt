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
import com.berkedursunoglu.kfnchatting.databinding.FragmentMessagePageBinding
import com.berkedursunoglu.kfnchatting.recyclerview.MessagePageRecyclerView
import com.berkedursunoglu.kfnchatting.viewmodels.SendMessageViewModels

class MessagePage : Fragment() {

    private lateinit var dataBinding:FragmentMessagePageBinding
    private lateinit var viewModel:SendMessageViewModels
    private lateinit var messagePageRecyclerView: MessagePageRecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        dataBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_message_page,container,false)
        viewModel = ViewModelProvider(this)[SendMessageViewModels::class.java]
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val string = arguments?.getString("id")
        val uid = arguments?.getString("uid")
        dataBinding.messageUsername.text = string
        dataBinding.messageSendmessage.setOnClickListener {
            var messageText = dataBinding.messageEditText.text.toString()
            messageText.let {
                messageSend(uid!!,it)
            }
        }
        dataBinding.messageRv.layoutManager = LinearLayoutManager(requireContext())
        viewModel.getMessage(uid!!)
        viewModel.messageLiveData.observe(viewLifecycleOwner, Observer {
            messagePageRecyclerView = MessagePageRecyclerView(it)
            dataBinding.messageRv.adapter = messagePageRecyclerView
        })
    }


    fun messageSend(anotherUserID:String,message:String){
        viewModel.sendMessage(anotherUserID,message)
    }



}