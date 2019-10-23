package com.nu9ve.quests.ui.main

import android.os.Bundle
import androidx.core.content.ContextCompat
import androidx.core.os.bundleOf
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.google.android.material.snackbar.Snackbar
import com.nu9ve.quests.R
import com.nu9ve.quests.domain.entity.ContactEntity
import com.nu9ve.quests.ui.contact.ContactViewModel
import com.nu9ve.quests.util.Event
import com.nu9ve.quests.util.withColor
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.toolbar.*
import javax.inject.Inject

/**
 *Decided to use Single-Activity approach, giving it the responsability to change between fragments
 * and to display common ui elements
 **/

class MainActivity: DaggerAppCompatActivity() {

    @Inject
    lateinit var mContactViewModel: ContactViewModel
    private val navController: NavController by lazy { findNavController(R.id.contactsNavigationFragment) }

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.AppTheme_NoActionBar)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupEvents()
        setSupportActionBar(toolbar)

    }


    private fun setupEvents() {


        mContactViewModel.run {
            toolbarTitle.observe(this@MainActivity, Observer {
                toolbar.title = it
            })

            snackbarText.observe(this@MainActivity, Observer<Event<Int>> { event ->
                event.getContentIfNotHandled()?.let {
                    Snackbar
                        .make(coordinatorLayout, getString(it), Snackbar.LENGTH_LONG)
                        .withColor(ContextCompat.getColor(this@MainActivity ,R.color.warningRed))
                        .show()
                }
            })

            eventGoToDetail.observe(this@MainActivity, Observer<Event<ContactEntity?>> { event ->
                event.getContentIfNotHandled()?.let {
                    navController.navigate(R.id.contactDetailsFragment, bundleOf("contact" to it))
                }
            })
        }
    }
}