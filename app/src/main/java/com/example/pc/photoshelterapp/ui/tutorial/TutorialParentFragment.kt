package com.example.pc.photoshelterapp.ui.tutorial

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.pc.photoshelterapp.R
import com.example.pc.photoshelterapp.databinding.FragmentTutorialParentBinding
import kotlinx.android.synthetic.main.fragment_tutorial_parent.*

class TutorialParentFragment : Fragment() {

    private lateinit var viewDataBinding: FragmentTutorialParentBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewDataBinding = FragmentTutorialParentBinding.inflate(inflater, container, false).apply {
            lifecycleOwner = lifecycleOwner
        }
        return viewDataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = TutorialPagerAdapter(childFragmentManager)
        val instructionsArray = resources.getStringArray(R.array.tutorialSteps)

        val firstFragment = TutorialPageFragment.newInstance(1, instructionsArray[0])
        val secondFragment = TutorialPageFragment.newInstance(2, instructionsArray[1])
        val thirdFragment = TutorialPageFragment.newInstance(3, instructionsArray[2])

        adapter.addFragment(firstFragment, "")
        adapter.addFragment(secondFragment, "")
        adapter.addFragment(thirdFragment, "")

        viewPagerLayout.adapter = adapter

        tabDots.setupWithViewPager(viewPagerLayout)
    }
}