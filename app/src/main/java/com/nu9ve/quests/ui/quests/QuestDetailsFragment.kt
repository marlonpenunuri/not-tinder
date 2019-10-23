package com.nu9ve.quests.ui.quests

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.nu9ve.quests.databinding.FragmentQuestsDetailBinding
import dagger.android.support.DaggerFragment

class QuestDetailsFragment: DaggerFragment() {

//    @Inject
//    lateinit var loginViewModel: LoginViewModel

    private lateinit var viewDataBinding: FragmentQuestsDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewDataBinding = FragmentQuestsDetailBinding.inflate(inflater, container, false)
        return viewDataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setEvents()
        setObservers()
    }

    private fun setEvents(){
//        viewDataBinding.loginButton.setOnClickListener {
//            loginViewModel.login(viewDataBinding.loginEmailText.text.toString(), viewDataBinding.loginPasswordText.text.toString())
//        }
    }

    private fun setObservers(){
//        loginViewModel.run {
//
//        }

    }
}