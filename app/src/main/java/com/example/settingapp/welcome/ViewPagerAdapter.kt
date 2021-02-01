package com.example.settingapp.welcome

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class ViewPagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {
    var oneFragment : OneFragment?=null
    var twoFragment : TwoFragment?=null
    override fun getItem(position: Int): Fragment {
        when (position) {
            0 -> {
                oneFragment= OneFragment();
                return oneFragment as OneFragment
            }
            1 -> {
                twoFragment= TwoFragment();
                return twoFragment as TwoFragment

            }
            2 -> {
                oneFragment= OneFragment();
                return oneFragment as OneFragment

            }
            3 -> {
                oneFragment= OneFragment();
                return oneFragment as OneFragment

            }
            4 -> {
                oneFragment= OneFragment();
                return oneFragment as OneFragment

            }
            else ->{
                oneFragment= OneFragment()
                return oneFragment as OneFragment

            }
        }
        throw IllegalStateException("No fragment at position $position")
    }

    override fun getCount(): Int {
        return 5
    }
}