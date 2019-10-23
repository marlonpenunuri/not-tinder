package com.example.pc.photoshelterapp.ui.tutorial

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import com.example.pc.photoshelterapp.R
import com.example.pc.photoshelterapp.databinding.FragmentTutorialParentBinding
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_tutorial_parent.*


/**
 *Hosts page fragments, provides tab indicator and gestures
 **/

class TutorialParentFragment : DaggerFragment() {

    private lateinit var viewDataBinding: FragmentTutorialParentBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        viewDataBinding = FragmentTutorialParentBinding.inflate(inflater, container, false)
        return viewDataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as AppCompatActivity).supportActionBar?.hide()

        val adapter = TutorialPagerAdapter(childFragmentManager)
        val instructionsArray = resources.getStringArray(R.array.tutorialSteps)

        val firstFragment = TutorialPageFragment.newInstance(1, instructionsArray[0])
        val secondFragment = TutorialPageFragment.newInstance(2, instructionsArray[1])
        val thirdFragment = TutorialPageFragment.newInstance(3, instructionsArray[2])
        val fourthFragment = TutorialPageFragment.newInstance(4, instructionsArray[3])

        adapter.addFragment(firstFragment, "")
        adapter.addFragment(secondFragment, "")
        adapter.addFragment(thirdFragment, "")
        adapter.addFragment(fourthFragment, "")

        viewPagerLayout.adapter = adapter

        tabDots.setupWithViewPager(viewPagerLayout)
    }

}