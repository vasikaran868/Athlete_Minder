package com.example.athleteminder.MainFragments

import android.animation.ObjectAnimator
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.DecelerateInterpolator
import android.view.animation.OvershootInterpolator
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.athleteminder.QuestionsViewModel
import com.example.athleteminder.QuestionsViewModelFactory
import com.example.athleteminder.databinding.FragmentResultScreenBinding


class ResultScreen : Fragment() {

    private var _binding : FragmentResultScreenBinding? = null
    val binding get() = _binding!!

    private val viewModel: QuestionsViewModel by activityViewModels {
        QuestionsViewModelFactory()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentResultScreenBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val animationpoa = ObjectAnimator.ofInt(binding.poaProgBar, "progress", 0, viewModel.poa!!)
        animationpoa.duration = 1500 // 3.5 second
        animationpoa.interpolator = OvershootInterpolator()
        animationpoa.start()

        val animationFocus = ObjectAnimator.ofInt(binding.focusProgBar, "progress", 0, viewModel.focus!!)
        animationFocus.duration = 1500 // 3.5 second
        animationFocus.interpolator = OvershootInterpolator()
        animationFocus.start()

        val animationIntensity = ObjectAnimator.ofInt(binding.intensityProgBar, "progress", 0, viewModel.intensity!!)
        animationIntensity.duration = 1500 // 3.5 second
        animationIntensity.interpolator = OvershootInterpolator()
        animationIntensity.start()

        val animationEating = ObjectAnimator.ofInt(binding.eatingProgBar, "progress", 0, viewModel.eating!!)
        animationEating.duration = 1500 // 3.5 second
        animationEating.interpolator = OvershootInterpolator()
        animationEating.start()

        val animationSleeping = ObjectAnimator.ofInt(binding.sleepingProgBar, "progress", 0, viewModel.sleeping!!)
        animationSleeping.duration = 1500 // 3.5 second
        animationSleeping.interpolator = OvershootInterpolator()
        animationSleeping.start()


    }

    override fun onResume() {
        super.onResume()

        val result = listOf<String>(
            "COME ON, YOU CAN DO BETTER",
            "IT IS GOOD, KEEP GOING",
            "MOVING IN A GREAT DIRECTION"
        )

        val avg = (viewModel.poa!! + viewModel.focus!! + viewModel.intensity!! + viewModel.eating!! + viewModel.sleeping!!)/5

        if (avg <= 30){
            binding.resultTv.text = result[0]
        }
        else if(avg > 30 && avg <= 50){
            binding.resultTv.text = result[1]
        }
        else{
            binding.resultTv.text = result[2]
        }

        binding.startBtn.setOnClickListener {
            activity?.finish()
        }


    }

}