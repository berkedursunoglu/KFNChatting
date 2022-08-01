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
import com.berkedursunoglu.kfnchatting.utils.Utils
import com.berkedursunoglu.kfnchatting.databinding.FragmentRegisterBinding
import com.berkedursunoglu.kfnchatting.viewmodels.RegisterFragmentViewModels

class RegisterFragment : Fragment() {

    private lateinit var dataBinding:FragmentRegisterBinding
    private lateinit var viewModel:RegisterFragmentViewModels

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        dataBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_register,container,false)
        viewModel = ViewModelProvider(this)[RegisterFragmentViewModels::class.java]
        dataBinding.fuctions = this
        return dataBinding.root
    }

    fun registerUser(){
        if (Utils.edittextNullCheckThreeVar(dataBinding.registerEmail.text.toString()
            ,dataBinding.registerPassword.text.toString()
            ,dataBinding.registerPasswordTwo.text.toString())){
            Toast.makeText(requireContext(),getString(R.string.null_edittext_message),Toast.LENGTH_SHORT).show()
        }else{
            viewModel.registerLogin(dataBinding.registerEmail.text.toString(),dataBinding.registerPassword.text.toString(),requireContext())
            viewModel.registerBoolean.observe(this, Observer {
                if(it){
                    view?.findNavController()?.navigate(RegisterFragmentDirections.actionRegisterFragmentToLoginFragment())
                    Toast.makeText(requireContext(),getString(R.string.register_succes_message),Toast.LENGTH_SHORT).show()
                }
            })
            viewModel.registerLogMessage.observe(this, Observer {
                if (it == getString(R.string.emailwronging)){
                    Log.d("RegisterFragment",it)
                    Toast.makeText(requireContext(),getString(R.string.emailwrongtr),Toast.LENGTH_SHORT).show()
                }else{
                    Toast.makeText(requireContext(),getString(R.string.passwordwrongtr),Toast.LENGTH_SHORT).show()
                }
                Log.d("RegisterFragment",it)

            })
        }
    }
}