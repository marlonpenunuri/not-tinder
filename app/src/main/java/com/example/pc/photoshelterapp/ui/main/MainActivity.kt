package com.example.pc.photoshelterapp.ui.main

import android.os.Bundle
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.example.pc.photoshelterapp.Event
import com.example.pc.photoshelterapp.R
import com.example.pc.photoshelterapp.domain.entities.ContactEntity
import com.example.pc.photoshelterapp.ui.contacts.ContactsViewModel
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.toolbar.*
import javax.inject.Inject

class MainActivity: DaggerAppCompatActivity() {

    @Inject
    lateinit var mContactsViewModel: ContactsViewModel
    private val navController: NavController by lazy { findNavController(R.id.mainActivityLayout) }

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.AppTheme_NoActionBar)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupEvents()
        setSupportActionBar(toolbar)

    }


    private fun setupEvents() {
        mContactsViewModel.run {
            toolbarTitle.observe(this@MainActivity, Observer {
                toolbar.title = it
            })

            dataLoading.observe(this@MainActivity, Observer { show ->
//                if (show) {
//                    progressBar.show()
//                } else {
//                    progressBar.dismiss()
//                }
            })

            snackbarText.observe(this@MainActivity, Observer<Event<String>> { event ->
                event.getContentIfNotHandled()?.let {
                    Toast.makeText(this@MainActivity, it, Toast.LENGTH_SHORT).show()
                }
            })

            eventGoToDetail.observe(this@MainActivity, Observer<Event<ContactEntity>> { event ->
                event.getContentIfNotHandled()?.let {
                    navController.navigate(R.id.contactDetailsFragment, bundleOf("contact" to it))
                }
            })
        }
    }
}