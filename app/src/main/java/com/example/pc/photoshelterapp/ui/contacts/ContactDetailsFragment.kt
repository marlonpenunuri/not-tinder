package com.example.pc.photoshelterapp.ui.contacts

import android.os.Bundle
import android.transition.TransitionInflater
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.navArgs
import androidx.transition.ChangeBounds
import com.example.pc.photoshelterapp.databinding.FragmentContactDetailsBinding
import com.squareup.picasso.Picasso
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_contact_details.*
import javax.inject.Inject

class ContactDetailsFragment: DaggerFragment() {

    private val arguments by navArgs<ContactListFragmentArgs>()
    private lateinit var viewDataBinding: FragmentContactDetailsBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewDataBinding = FragmentContactDetailsBinding.inflate(inflater, container, false).apply {
            contact = arguments.contact
            lifecycleOwner = lifecycleOwner
        }

        sharedElementEnterTransition = TransitionInflater.from(context).inflateTransition(android.R.transition.move)
        return viewDataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as AppCompatActivity).supportActionBar?.hide()
        Picasso.get().load(arguments.contact.picture.large).into(userImageView)
    }

    override fun onDestroy() {
        super.onDestroy()
        (activity as AppCompatActivity).supportActionBar?.show()
    }

}