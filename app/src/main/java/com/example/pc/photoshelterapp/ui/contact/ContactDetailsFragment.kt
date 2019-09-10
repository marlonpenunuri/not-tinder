package com.example.pc.photoshelterapp.ui.contact

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.navArgs
import com.example.pc.photoshelterapp.R
import com.example.pc.photoshelterapp.databinding.FragmentContactDetailsBinding
import com.example.pc.photoshelterapp.util.CropCircleTransformation
import com.squareup.picasso.Picasso
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_contact_details.*


class ContactDetailsFragment: DaggerFragment() {

    private val arguments by navArgs<ContactListFragmentArgs>()
    private lateinit var viewDataBinding: FragmentContactDetailsBinding


    /**
     *Service returns proper names without a capital letters, so i had to workaround databinding
     * to use the capitalize() method instead of using a more complicated approach
     **/
    @SuppressLint("DefaultLocale")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewDataBinding = FragmentContactDetailsBinding.inflate(inflater, container, false).apply {
            contact = arguments.contact
            lifecycleOwner = lifecycleOwner
            with(arguments.contact) {
                fullName = "${name.title.capitalize()} ${name.first.capitalize()} ${name.last.capitalize()}"
            }
        }
        return viewDataBinding.root
    }


    /**
     *I set up the image without databinding to avoid having problems with picasso
     **/
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as AppCompatActivity).supportActionBar?.hide()
        Picasso.get().load(arguments.contact.picture.large)
            .transform(CropCircleTransformation())
            .placeholder(R.drawable.ic_unknown_user)
            .into(userImageView)
        setEvents()
    }

    override fun onDestroy() {
        super.onDestroy()
        (activity as AppCompatActivity).supportActionBar?.show()
    }

    private fun setEvents(){
        with(viewDataBinding){
            facebookBtn.setOnClickListener {
                openUrl("https://www.facebook.com/")
            }

            twitterBtn.setOnClickListener {
                openUrl("https://www.twitter.com")
            }

            userCellView.setOnClickListener {
                openPhoneApp(arguments.contact.cell)
            }
        }
    }

    private fun openUrl(url: String){
        val openURL = Intent(Intent.ACTION_VIEW)
        openURL.data = Uri.parse(url)
        startActivity(openURL)
    }

    private fun openPhoneApp(phoneNumber: String){
        val dialIntent = Intent(Intent.ACTION_DIAL)
        dialIntent.data = Uri.parse("tel:$phoneNumber")
        startActivity(dialIntent)
    }

}