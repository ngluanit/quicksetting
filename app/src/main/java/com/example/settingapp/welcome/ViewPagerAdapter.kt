package com.example.settingapp.welcome

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class ViewPagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {
    var oneFragment : OneFragment?=null
    var twoFragment : TwoFragment?=null
    var threeFragment : ThreeFragment?=null
    var FourFragment : FourFragment?=null
    var FiveFragment : FiveFragment?=null
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
                threeFragment= ThreeFragment();
                return threeFragment as ThreeFragment

            }
            3 -> {
                FourFragment= FourFragment();
                return FourFragment as FourFragment

            }
            4 -> {
                FiveFragment= FiveFragment();
                return FiveFragment as FiveFragment

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