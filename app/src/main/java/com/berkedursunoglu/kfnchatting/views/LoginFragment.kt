package com.berkedursunoglu.kfnchatting.views

import android.content.Intent
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
import androidx.navigation.findNavController
import com.berkedursunoglu.kfnchatting.R
import com.berkedursunoglu.kfnchatting.databinding.FragmentLoginBinding
import com.berkedursunoglu.kfnchatting.utils.Utils
import com.berkedursunoglu.kfnchatting.viewmodels.LoginFragmentViewModels


class LoginFragment : Fragment() {

    private lateinit var dataBinding:FragmentLoginBinding
    private lateinit var viewModel:LoginFragmentViewModels


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        dataBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_login,container,false)
        viewModel = ViewModelProvider(this)[LoginFragmentViewModels::class.java]
        dataBinding.fuctions = this
        return dataBinding.root
    }

    fun loginUser(){
        if (Utils.edittextNullCheckTwoVar(dataBinding.loginEmail.text.toString(),dataBinding.loginPassword.text.toString())){
            Toast.makeText(requireContext(),getString(R.string.null_edittext_message),Toast.LENGTH_SHORT).show()
        }else{
            viewModel.loginUser(dataBinding.loginEmail.text.toString(),dataBinding.loginPassword.text.toString(),requireContext())
            viewModel.loginBoolean.observe(this, Observer {
                if (it){
                    startActivity(Intent(requireContext(),MainPage::class.java))
                    requireActivity().finish()
                }
            })
            viewModel.loginLogMessage.observe(this, Observer {
                Toast.makeText(requireContext(),getString(R.string.login_error_message),Toast.LENGTH_SHORT).show()
                Log.d("LoginFragment",it)
            })
        }
    }

}