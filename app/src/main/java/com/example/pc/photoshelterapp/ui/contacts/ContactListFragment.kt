package com.example.pc.photoshelterapp.ui.contacts

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.pc.photoshelterapp.Event
import com.example.pc.photoshelterapp.R
import com.example.pc.photoshelterapp.databinding.FragmentContactListBinding
import com.example.pc.photoshelterapp.domain.entities.ContactEntity
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class ContactListFragment: DaggerFragment() {

    @Inject
    lateinit var mHomeViewModel: ContactsViewModel
    private lateinit var viewDataBinding: FragmentContactListBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        viewDataBinding = FragmentContactListBinding.inflate(inflater, container, false).apply {
            viewModel = mHomeViewModel
            adapter = ContactListAdapter(mHomeViewModel ){}
            lifecycleOwner = lifecycleOwner
        }
        return viewDataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)
        setObservers()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewDataBinding.viewModel?.getContacts()
    }

    private fun setObservers(){
        mHomeViewModel.run {
            contactList.observe(viewLifecycleOwner, Observer {
                viewDataBinding.adapter?.submitList(it)
            })

//            eventGoToDetail.observe(viewLifecycleOwner, Observer<Event<Unit>> { event ->
//                event.getContentIfNotHandled()?.let {
//                    findNavController().navigate(R.id.contactDetailsFragment)
//                }
//            })
        }

    }

}