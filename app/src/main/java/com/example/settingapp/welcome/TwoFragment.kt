package com.example.settingapp.welcome

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import android.provider.Settings.SettingNotFoundException
import android.text.TextUtils.SimpleStringSplitter
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import com.example.settingapp.R
import com.example.settingapp.util.MyAccessibilityService
import kotlinx.android.synthetic.main.activity_sliders.*
import kotlinx.android.synthetic.main.two_fragment.*
import kotlinx.android.synthetic.main.two_fragment.view.*


class TwoFragment : Fragment() {

    private var imgTurnAcessShown = true
    private var imgTurnOverlayShown = true
    private var imgTurnShowShown = true
    private val REQUEST_ACCESSIBILITY = 777

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View
        view = inflater.inflate(R.layout.two_fragment, container, false)
        view.imgTurnAcess.setOnClickListener {
            if ((imgTurnAcess != null) && (imgTurnAcessShown)){
                imgTurnAcess.setImageResource(R.drawable.ic_open);
                imgTurnAcessShown = false
                acesspermission(activity!!)
            }
            else{
                if (imgTurnAcess != null) imgTurnAcess.setImageResource(R.drawable.ic_switch_off);
                imgTurnAcessShown = true
            }
        }
        view.imgTurnOverlay.setOnClickListener {
            if ((imgTurnOverlay != null) && (imgTurnOverlayShown)){
                imgTurnOverlay.setImageResource(R.drawable.ic_open);
                imgTurnOverlayShown = false
                overlay(activity!!)
            }
            else{
                if (imgTurnOverlay != null) imgTurnOverlay.setImageResource(R.drawable.ic_switch_off);
                imgTurnOverlayShown = true
            }
        }
        view.imgTurnShow.setOnClickListener {
            if ((imgTurnShow != null) && (imgTurnShowShown)){
                imgTurnShow.setImageResource(R.drawable.ic_open);
                imgTurnShowShown = false
                startActivity(Intent("android.settings.ACTION_NOTIFICATION_LISTENER_SETTINGS"));
            }
            else{
                if (imgTurnShow != null) imgTurnShow.setImageResource(R.drawable.ic_switch_off);
                imgTurnShowShown = true
            }

        }
        return view
    }

    private fun isAccessibilitySettingsOn(mContext: Context): Boolean {
        try {
            var accessibilityEnabled = 0
            val service: String =
                mContext.getPackageName() + "/" + MyAccessibilityService::class.java.getCanonicalName()
            try {
                accessibilityEnabled = Settings.Secure.getInt(
                    mContext.applicationContext.contentResolver,
                    Settings.Secure.ACCESSIBILITY_ENABLED
                )
                Log.v("TAG", "accessibilityEnabled = $accessibilityEnabled")
            } catch (e: SettingNotFoundException) {
                Log.e(
                    "TAG", "Error finding setting, default accessibility to not found: "
                            + e.message
                )
            }
            val mStringColonSplitter = SimpleStringSplitter(':')
            if (accessibilityEnabled == 1) {
                Log.v("TAG", "***ACCESSIBILITY IS ENABLED*** -----------------")
                val settingValue = Settings.Secure.getString(
                    mContext.applicationContext.contentResolver,
                    Settings.Secure.ENABLED_ACCESSIBILITY_SERVICES
                )
                if (settingValue != null) {
                    mStringColonSplitter.setString(settingValue)
                    while (mStringColonSplitter.hasNext()) {
                        val accessibilityService = mStringColonSplitter.next()
                        Log.v(
                            "TAG",
                            "-------------- > accessibilityService :: $accessibilityService $service"
                        )
                        if (accessibilityService.equals(service, ignoreCase = true)) {
                            Log.v(
                                "TAG",
                                "We've found the correct setting - accessibility is switched on!"
                            )
                            return true
                        }
                    }
                }
            } else {
                Log.v("TAG", "***ACCESSIBILITY IS DISABLED***")
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return false
    }

    private fun acesspermission(mContext: Context) {
//        Intent intent=new Intent(this, MaintwoActivity.class);
//        startActivity(intent);
        if (!isAccessibilitySettingsOn(mContext)) {
            var intent = Intent("com.samsung.accessibility.installed_service")
            println("mzmzmamama///${mContext.packageName}")
            if (intent.resolveActivity(mContext.packageManager) == null) {
                intent = Intent(Settings.ACTION_ACCESSIBILITY_SETTINGS)
            }
            try {
                startActivityForResult(
                    intent,
                    REQUEST_ACCESSIBILITY
                )
            } catch (e: java.lang.Exception) {
                startActivityForResult(
                    Intent(Settings.ACTION_ACCESSIBILITY_SETTINGS),
                    REQUEST_ACCESSIBILITY
                )
            }
        } else {
            val returnIntent = Intent()
            returnIntent.putExtra("result", "Enable")
            activity?.setResult(Activity.RESULT_OK, returnIntent)
        }
    }
    @RequiresApi(Build.VERSION_CODES.M)
    private fun overlay(mContext: Context){
        if (!Settings.canDrawOverlays(mContext)) {
            val REQUEST_CODE = 101
            val myIntent =
                Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION)
            myIntent.data = Uri.parse("package:" + mContext.getPackageName())
            startActivityForResult(myIntent, REQUEST_CODE)
        }
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == REQUEST_ACCESSIBILITY && resultCode == Activity.RESULT_OK) {
            val returnIntent = Intent()
            returnIntent.putExtra("result", "Enable")
            activity?.setResult(Activity.RESULT_OK, returnIntent)
            activity?.finish()
        }else if (requestCode==101){
            val returnIntent = Intent()
            returnIntent.putExtra("result", "Enable")
            activity?.setResult(Activity.RESULT_OK, returnIntent)
            activity?.finish()
        }
    }
}