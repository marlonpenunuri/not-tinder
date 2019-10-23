package com.example.pc.photoshelterapp.ui.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.pc.photoshelterapp.R
import com.example.pc.photoshelterapp.databinding.FragmentLoginBinding
import com.example.pc.photoshelterapp.util.withColor
import com.google.android.material.snackbar.Snackbar
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_login.*
import javax.inject.Inject

class LoginFragment: DaggerFragment() {

    @Inject
    lateinit var loginViewModel: LoginViewModel

    private lateinit var viewDataBinding: FragmentLoginBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewDataBinding = FragmentLoginBinding.inflate(inflater, container, false)
        return viewDataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setEvents()
        setObservers()
    }

    private fun setEvents(){
        viewDataBinding.loginButton.setOnClickListener {
            loginViewModel.login(viewDataBinding.loginEmailText.text.toString(), viewDataBinding.loginPasswordText.text.toString())
        }
    }

    private fun setObservers(){
        loginViewModel.run {
            correctLoginEvent.observe(viewLifecycleOwner, Observer { event ->
                event.getContentIfNotHandled()?.let {
                    if(it){
                        findNavController().navigate(R.id.goToTutorialFragment)
                    } else {
                        Snackbar
                            .make(loginLayout, "Tu no eres mi amo", Snackbar.LENGTH_LONG)
                            .withColor(ContextCompat.getColor(activity!!, R.color.warningRed))
                            .show()
                    }
                }
            })
        }

    }
}