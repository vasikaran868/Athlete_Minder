package com.example.athleteminder.MainFragments

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.athleteminder.R
import com.example.athleteminder.databinding.FragmentSplashScreenBinding

class splash_screen : Fragment() {

    private var _binding : FragmentSplashScreenBinding? = null
    val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSplashScreenBinding.inflate(inflater, container, false)
        return  binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        val runnable = Runnable {
//            val action = splash_screenDirections.actionSplashScreenToQuestionsScreen()
//            findNavController().navigate(action)
//        }
//        val handler = Handler(Looper.getMainLooper())
//        handler.postDelayed(runnable, 2500)

        binding.startBtn.setOnClickListener {
            val action = splash_screenDirections.actionSplashScreenToQuestionsScreen()
            findNavController().navigate(action)
        }
    }

}