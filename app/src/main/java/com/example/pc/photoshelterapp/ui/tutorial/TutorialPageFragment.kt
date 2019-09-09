package com.example.pc.photoshelterapp.ui.tutorial

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.pc.photoshelterapp.R
import com.example.pc.photoshelterapp.databinding.FragmentTutorialPageBinding
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_tutorial_page.*

class TutorialPageFragment : DaggerFragment() {


    companion object {
        fun newInstance(step: Int, instruction: String) : TutorialPageFragment {
            return TutorialPageFragment().apply {
                arguments = Bundle().apply {
                    putInt("currentStep", step)
                    putString("INSTRUCTION", instruction)
                }
            }
        }
    }

    private lateinit var viewDataBinding: FragmentTutorialPageBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewDataBinding = FragmentTutorialPageBinding.inflate(inflater, container, false).apply {
            lifecycleOwner = lifecycleOwner
        }
        return viewDataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.let {
            pageInstructionText.text = it.getString("INSTRUCTION")
            if(it.getInt("currentStep")  == 3){
                endTutorialBtn.run {
                    visibility = View.VISIBLE
                    setOnClickListener {
                        findNavController().navigate(R.id.goToContactListFragment)
                    }
                }
            }
        }


    }

}