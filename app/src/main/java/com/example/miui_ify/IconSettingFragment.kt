package com.example.miui_ify

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.settingapp.R

class IconSettingFragment : Fragment() {
    fun newInstance(page: Int, title: String?): IconSettingFragment? {
        val fragmentFirst = IconSettingFragment()
        val args = Bundle()
//        args.putInt("someInt", page)
//        args.putString("someTitle", title)
//        fragmentFirst.setArguments(args)
        return fragmentFirst
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.icon_setting_fragment, container, false)
        return view
    }
}