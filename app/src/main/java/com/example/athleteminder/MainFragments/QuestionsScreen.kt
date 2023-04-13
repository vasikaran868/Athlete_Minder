package com.example.athleteminder.MainFragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.example.athleteminder.Helper.QuestionsViewPagerAdapter
import com.example.athleteminder.Models.Question
import com.example.athleteminder.databinding.FragmentQuestionsScreenBinding
import com.google.android.material.tabs.TabLayoutMediator

class QuestionsScreen : Fragment() {

    private var _binding : FragmentQuestionsScreenBinding? = null
    val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentQuestionsScreenBinding.inflate(inflater, container, false)

        val frag_list = arrayListOf<Fragment>(
            Question_vp_screen(
                Question(
                    question = "Did you think about what you want to do and how to do it?",
                    maxAnswer = "BRINGING IT",
                    minAnswer = "WINGING IT",
                    topic = "PLAN OF ACTION"
                )
            ),
            Question_vp_screen(
                Question(
                    question = "Did you concentrate or your mind somewhere else?",
                    maxAnswer = "DAILED IN",
                    minAnswer = "DISTRACTED",
                    topic = "FOCUS"
                )
            ),
            Question_vp_screen(
                Question(
                    question = "Did you practice the way you want to play?",
                    maxAnswer = "TOTALLY INTO IT",
                    minAnswer = "JUST GOT THROUGH IT",
                    topic = "INTENSITY"
                )
            ),
            Question_vp_screen(
                Question(
                    question = "Did the way you ate today make you feel...?",
                    maxAnswer = "FUELED",
                    minAnswer = "DRAINED",
                    topic = "EATING"
                )
            ),
            Question_vp_screen(
                Question(
                    question = "Did you wake up feeling...?",
                    maxAnswer = "TOTALLY RESTED",
                    minAnswer = "TIRED",
                    topic = "SLEEPING"
                )
            )
        )
        val topics = listOf<String>("PLAN OF ACTION", "FOCUS", "INTENSITY", "EATING", "SLEEPING")
        val adapter = QuestionsViewPagerAdapter(
            frag_list,
            requireActivity().supportFragmentManager,
            lifecycle
        )

        binding.questionsViewpager.adapter = adapter
        binding.questionsViewpager.reduceDragSensitivity()
        TabLayoutMediator(binding.tabLayout, binding.questionsViewpager) { tab, position ->
            //Some implementation
        }.attach()

        binding.questionsViewpager.registerOnPageChangeCallback(object :ViewPager2.OnPageChangeCallback(){
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                binding.topicTv.text = topics[position]
                if (position == 0){
                    binding.apply {
                        previousIv.visibility = View.GONE
                        exitTv.visibility = View.VISIBLE
                        nextIv.visibility = View.VISIBLE
                        submitTv.visibility = View.GONE
                    }
                }
                else if (position == frag_list.size - 1){
                    binding.apply {
                        previousIv.visibility = View.VISIBLE
                        exitTv.visibility = View.GONE
                        nextIv.visibility = View.GONE
                        submitTv.visibility = View.VISIBLE
                    }
                }
                else{
                    binding.apply {
                        previousIv.visibility = View.VISIBLE
                        exitTv.visibility = View.GONE
                        nextIv.visibility = View.VISIBLE
                        submitTv.visibility = View.GONE
                    }
                }

            }
        })

        binding.nextIv.setOnClickListener {
            binding.questionsViewpager.currentItem = binding.questionsViewpager.currentItem + 1
        }
        binding.previousIv.setOnClickListener {
            binding.questionsViewpager.currentItem = binding.questionsViewpager.currentItem - 1
        }

        binding.submitTv.setOnClickListener {
            val action = QuestionsScreenDirections.actionQuestionsScreenToResultScreen()
            findNavController().navigate(action)
        }

        return binding.root
    }

    fun ViewPager2.reduceDragSensitivity() {
        val recyclerViewField = ViewPager2::class.java.getDeclaredField("mRecyclerView")
        recyclerViewField.isAccessible = true
        val recyclerView = recyclerViewField.get(this) as RecyclerView

        val touchSlopField = RecyclerView::class.java.getDeclaredField("mTouchSlop")
        touchSlopField.isAccessible = true
        val touchSlop = touchSlopField.get(recyclerView) as Int
        touchSlopField.set(recyclerView, touchSlop*2)
    }

}