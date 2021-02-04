package com.example.settingapp.welcome

import android.Manifest
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.settingapp.R
import com.example.settingapp.tiles.TileOptionsActivity
import kotlinx.android.synthetic.main.three_fragment.*
import kotlinx.android.synthetic.main.three_fragment.view.*

class ThreeFragment : Fragment() {
    private val REQUEST_READ_PERMISSION=222
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View
        view = inflater.inflate(R.layout.three_fragment, container, false)
        view.rlNone.setOnClickListener {
            tick_none.visibility = View.VISIBLE
            tick_draw.visibility = View.INVISIBLE
            tick_noti.visibility = View.INVISIBLE
        }
        view.rlBlurWall.setOnClickListener {
            acesspermission()
        }
        view.rlBlur.setOnClickListener {
            val intent = Intent (getActivity(), BlurBackgroundActivity::class.java)
            getActivity()!!.startActivity(intent)
            tick_draw.visibility = View.VISIBLE
            tick_none.visibility = View.INVISIBLE
            tick_noti.visibility = View.INVISIBLE
        }
        return view
    }


    private fun acesspermission() {
        tick_none.visibility = View.INVISIBLE
        tick_draw.visibility = View.INVISIBLE
        tick_noti.visibility = View.VISIBLE
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions(
                arrayOf(
                    Manifest.permission.WRITE_EXTERNAL_STORAGE
                ), REQUEST_READ_PERMISSION
            )
        }else{
            Toast.makeText(context,"Grand Storage",Toast.LENGTH_LONG).show()
        }
    }

}
