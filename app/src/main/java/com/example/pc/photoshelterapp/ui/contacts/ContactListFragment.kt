package com.example.pc.photoshelterapp.ui.contacts

import android.os.Bundle
import android.view.*
import androidx.appcompat.widget.SearchView
import androidx.core.os.bundleOf
import androidx.lifecycle.Observer
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.pc.photoshelterapp.Event
import com.example.pc.photoshelterapp.R
import com.example.pc.photoshelterapp.databinding.FragmentContactListBinding
import com.example.pc.photoshelterapp.domain.entities.ContactEntity
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_contact_list.*
import kotlinx.android.synthetic.main.item_contact.*
import javax.inject.Inject

class ContactListFragment: DaggerFragment() {

    @Inject
    lateinit var mContactsViewModel: ContactsViewModel
    private lateinit var viewDataBinding: FragmentContactListBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        viewDataBinding = FragmentContactListBinding.inflate(inflater, container, false).apply {
            viewModel = mContactsViewModel
            adapter = ContactListAdapter(mContactsViewModel ){}
            lifecycleOwner = lifecycleOwner
        }
        setHasOptionsMenu(true)
        return viewDataBinding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setObservers()
        viewDataBinding.viewModel?.getContacts()
    }

    private fun setObservers(){
        mContactsViewModel.run {
            contactList.observe(viewLifecycleOwner, Observer {
                viewDataBinding.adapter?.submitList(it)
            })

            refreshingList.observe(viewLifecycleOwner, Observer {
                swipeRefreshLayout.isRefreshing = it
            })

            eventGoToDetail.observe(viewLifecycleOwner, Observer<Event<ContactEntity>> { event ->
                event.getContentIfNotHandled()?.let {
                    val extras = FragmentNavigatorExtras(
                        userImageView to "userImageView",
                        userNameView to "userNameView")
                    findNavController().navigate(R.id.goToContactDetailsFragment,
                        bundleOf("contact" to it), // Bundle of args
                        null, // NavOptions
                        extras)
                }
            })
        }

        swipeRefreshLayout.setOnRefreshListener {
           mContactsViewModel.resetList()
        }
    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu_searchbar, menu)

        val searchView = menu.findItem(R.id.menu_nav_search_bar)
        val searchItem = searchView.actionView as SearchView

        searchItem.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(newText: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                viewDataBinding.adapter
                return true
            }

        })

    }

}