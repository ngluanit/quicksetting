package com.example.settingapp.welcome

import android.Manifest
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.settingapp.R
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
        view.rlBlurWall.setOnClickListener {
            acesspermission()
        }
        return view
    }

    private fun acesspermission() {
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
