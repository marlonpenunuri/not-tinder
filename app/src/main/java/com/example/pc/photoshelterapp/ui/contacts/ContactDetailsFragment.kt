package com.example.pc.photoshelterapp.ui.contacts

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.pc.photoshelterapp.databinding.FragmentContactDetailsBinding
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class ContactDetailsFragment: DaggerFragment() {

    @Inject
    lateinit var mHomeViewModel: ContactsViewModel
    private lateinit var viewDataBinding: FragmentContactDetailsBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewDataBinding = FragmentContactDetailsBinding.inflate(inflater, container, false).apply {
            viewModel = mHomeViewModel
            lifecycleOwner = lifecycleOwner
        }
        return viewDataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)
        viewDataBinding.viewModel?.getContactDetails()
    }

}