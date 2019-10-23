package com.nu9ve.quests.ui.tutorial

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.nu9ve.quests.R
import com.nu9ve.quests.databinding.FragmentTutorialPageBinding
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_tutorial_page.*


/**
 *Generic fragment with a textview
 **/
class TutorialPageFragment : DaggerFragment() {

    companion object {
        fun newInstance(step: Int, instruction: String) : TutorialPageFragment {
            return TutorialPageFragment().apply {
                arguments = Bundle().apply {
                    putInt("STEP", step)
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
        viewDataBinding = FragmentTutorialPageBinding.inflate(inflater, container, false)
        return viewDataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.let {
            pageInstructionText.text = it.getString("INSTRUCTION")
            if(it.getInt("STEP")  == 4){
                endTutorialBtn.run {
                    visibility = View.VISIBLE
                    setOnClickListener {
                        findNavController().navigate(R.id.goToMapFragment)
                    }
                }
            }
        }

    }

}