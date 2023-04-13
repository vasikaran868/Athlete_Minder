package com.example.athleteminder.Helper

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter

class QuestionsViewPagerAdapter(list :ArrayList<Fragment>, fm: FragmentManager, lifecycle: Lifecycle): FragmentStateAdapter(fm,lifecycle){

    private val fragment_list = list
    override fun getItemCount(): Int {
        return fragment_list.size
    }

    override fun createFragment(position: Int): Fragment {
        return fragment_list[position]
    }
}
