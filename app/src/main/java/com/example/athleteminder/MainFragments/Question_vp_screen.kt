package com.example.athleteminder.MainFragments

import android.animation.ObjectAnimator
import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.View.OnTouchListener
import android.view.ViewGroup
import android.view.animation.OvershootInterpolator
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.athleteminder.Models.Question
import com.example.athleteminder.QuestionsViewModel
import com.example.athleteminder.QuestionsViewModelFactory
import com.example.athleteminder.databinding.FragmentQuestionVpScreenBinding
import java.math.RoundingMode
import java.text.DecimalFormat


class Question_vp_screen(question: Question) : Fragment() {

    val question = question

    private val viewModel: QuestionsViewModel by activityViewModels {
        QuestionsViewModelFactory()
    }

    private var _binding : FragmentQuestionVpScreenBinding? = null
    val binding get() = _binding!!

    var dropY : Float? = null
    var moveY : Float? = null

    val df = DecimalFormat("#.#")
    val dc = DecimalFormat("#.#")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentQuestionVpScreenBinding.inflate(inflater,container,false)
        binding.quesTv.text = question.question
        binding.maxAnsTv.text = question.maxAnswer
        binding.minAnsTv.text = question.minAnswer
        return binding.root
    }

    override fun onResume() {
        super.onResume()
    }


    @SuppressLint("ClickableViewAccessibility")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        df.roundingMode = RoundingMode.FLOOR
        dc.roundingMode = RoundingMode.CEILING

        when(question.topic){
            "PLAN OF ACTION" -> viewModel.poa = (binding.progBar.progress/100 ).toInt()
            "FOCUS" -> viewModel.focus = (binding.progBar.progress/100 ).toInt()
            "INTENSITY" -> viewModel.intensity = (binding.progBar.progress/100 ).toInt()
            "EATING" -> viewModel.eating = (binding.progBar.progress/100 ).toInt()
            "SLEEPING" -> viewModel.sleeping = (binding.progBar.progress/100 ).toInt()
        }
        val prog = binding.progBar.progress/10000
        binding.apply {
            maxAnsTv.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20 + df.format(prog*15).toFloat())
            minAnsTv.setTextSize(TypedValue.COMPLEX_UNIT_SP, 35 - df.format(prog*15).toFloat())
            maxAnsTv.alpha = (.5 + (prog * 0.5)).toFloat()
            minAnsTv.alpha = (1 - (prog * 0.5)).toFloat()
        }

        binding.progBar.setOnTouchListener(object: OnTouchListener {
            override fun onTouch(v: View?, event: MotionEvent?): Boolean {
                when (event!!.action) {
                    MotionEvent.ACTION_DOWN -> {
                        dropY = event.y
                        moveY = event.y
                    }
                    MotionEvent.ACTION_MOVE -> {
                        val diff = event.y - moveY!!
                        if (diff < 0){
                            val progress = binding.progBar.progress + (diff*-10000/binding.root.height)
                            binding.progBar.setProgress(progress.toInt())
                            val prog = progress/10000
                            binding.apply {
                                maxAnsTv.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20 + dc.format(prog*15).toFloat())
                                minAnsTv.setTextSize(TypedValue.COMPLEX_UNIT_SP, 35 - dc.format(prog*15).toFloat())
                                maxAnsTv.alpha = (.5 + (prog * 0.5)).toFloat()
                                minAnsTv.alpha = (1 - (prog * 0.5)).toFloat()
                            }
                        }
                        else{
                            val progress = binding.progBar.progress - (diff*10000/binding.root.height).toInt()
                            binding.progBar.setProgress(progress)
                            val prog = progress.toFloat()/10000
                            binding.apply {
                                maxAnsTv.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20 + df.format(prog*15).toFloat())
                                minAnsTv.setTextSize(TypedValue.COMPLEX_UNIT_SP, 35 - df.format(prog*15).toFloat())
                                maxAnsTv.alpha = (.5 + (prog * 0.5)).toFloat()
                                minAnsTv.alpha = (1 - (prog * 0.5)).toFloat()
                            }

                        }
                        moveY = event.y
                    }
                    MotionEvent.ACTION_UP -> {
                        dropY = null
                        moveY = null
                        when(question.topic){
                            "PLAN OF ACTION" -> viewModel.poa = (binding.progBar.progress/100 ).toInt()
                            "FOCUS" -> viewModel.focus = (binding.progBar.progress/100 ).toInt()
                            "INTENSITY" -> viewModel.intensity = (binding.progBar.progress/100 ).toInt()
                            "EATING" -> viewModel.eating = (binding.progBar.progress/100 ).toInt()
                            "SLEEPING" -> viewModel.sleeping = (binding.progBar.progress/100 ).toInt()
                        }
                    }
                }
                return true
            }

        })

    }

}