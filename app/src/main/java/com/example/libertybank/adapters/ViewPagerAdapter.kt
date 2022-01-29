package com.example.libertybank.adapters

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.libertybank.fragments.CardFirstFragment
import com.example.libertybank.fragments.CardSecondFragment
import com.example.libertybank.fragments.HomeFragment

class ViewPagerAdapter (fragment: HomeFragment): FragmentStateAdapter(fragment) {
    override fun getItemCount() = 3

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> CardFirstFragment()
            1 -> CardSecondFragment()
            else -> CardFirstFragment()
        }
    }


}