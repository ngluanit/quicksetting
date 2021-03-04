package com.example.settingapp.handles

import android.Manifest
import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import android.util.DisplayMetrics
import android.util.Log
import android.view.Display
import android.view.View
import android.view.Window
import android.widget.RelativeLayout
import android.widget.SeekBar
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.view.marginLeft
import androidx.core.view.marginRight
import com.example.blacklist.BlacklistActivity
import com.example.miui_ify.FloatingWindow
import com.example.miui_ify.MainActivity
import com.example.settingapp.R
import com.example.settingapp.required.PermissionRequired
import com.example.settingapp.util.SharePref
import kotlinx.android.synthetic.main.activity_colors.imgBack
import kotlinx.android.synthetic.main.activity_handles.*
import kotlinx.android.synthetic.main.dialog_draw_handles.*
import kotlinx.android.synthetic.main.layout_handle_seekbar.*

class HandlesActivity : AppCompatActivity() {
    private var imgTurnFullleghtShown = true
    private var imgTurnHideHandleShown = true
    private var imgTurnHideFullScreenShown = true
    private var imgTurnHidelandscapeShown = true
    private var imgTurnHideKeyboardOpenShown = true
    private var imgTurnDisableHandleShown = false
    private var imgTurnDisableleftHandleShown = true
    private var imgTurnDisableRightHandleShown = true
    private val REQUEST_ACCESSIBILITY = 777
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_handles)
        val window: Window = this.getWindow()
        getWindow().decorView.systemUiVisibility =
            View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR //  set status text dark

        window.statusBarColor = ContextCompat.getColor(this, R.color.white)

        Showdialog()

        setTurnHandle()
        setDataSeekbar()
        imgBack.setOnClickListener {
          onBackPressed()
        }
        rlBlackList.setOnClickListener {
            val intent = Intent(this, BlacklistActivity::class.java);
            startActivity(intent)
        }
    }
    private fun setTurnHandle(){
        if (SharePref.getBooleanPref(applicationContext,"full_length")){
            viewfull.visibility=View.VISIBLE
            view.visibility=View.INVISIBLE
        }else{
            viewfull.visibility=View.INVISIBLE
            view.visibility=View.VISIBLE
        }
        if (SharePref.getBooleanPref(applicationContext,"hide_length")){
            viewfull.visibility=View.INVISIBLE
            view.visibility=View.INVISIBLE
        }else{
            if (SharePref.getBooleanPref(applicationContext,"full_length")){
                viewfull.visibility=View.VISIBLE
                view.visibility=View.INVISIBLE
            }else{
                viewfull.visibility=View.INVISIBLE
                view.visibility=View.VISIBLE
            }
        }
        imgTurnFullleght.setOnClickListener {
            if ((imgTurnFullleght != null) && (imgTurnFullleghtShown)){
                imgTurnFullleght.setImageResource(R.drawable.ic_switch_on);
                imgTurnFullleghtShown = false
                viewfull.visibility=View.VISIBLE
                view.visibility=View.INVISIBLE
                SharePref.setBooleanPref(applicationContext,"full_length",true)
            }
            else{
                if (imgTurnFullleght != null) imgTurnFullleght.setImageResource(R.drawable.ic_switch_off);
                imgTurnFullleghtShown = true
                viewfull.visibility=View.INVISIBLE
                view.visibility=View.VISIBLE
                SharePref.setBooleanPref(applicationContext,"full_length",false)

            }
        }

        imgTurnHideHandle.setOnClickListener {
            if ((imgTurnHideHandle != null) && (imgTurnHideHandleShown)){
                imgTurnHideHandle.setImageResource(R.drawable.ic_switch_on);
                imgTurnHideHandleShown = false
                viewfull.visibility=View.INVISIBLE
                view.visibility=View.INVISIBLE
                SharePref.setBooleanPref(applicationContext,"hide_length",true)
            }
            else{
                if (imgTurnHideHandle != null) imgTurnHideHandle.setImageResource(R.drawable.ic_switch_off);
                imgTurnHideHandleShown = true
                if (SharePref.getBooleanPref(applicationContext,"full_length")){
                    viewfull.visibility=View.VISIBLE
                    view.visibility=View.INVISIBLE
                }else{
                    viewfull.visibility=View.INVISIBLE
                    view.visibility=View.VISIBLE
                }
                SharePref.setBooleanPref(applicationContext,"hide_length",false)
            }
        }

        imgTurnHideFullScreen.setOnClickListener {
            if ((imgTurnHideFullScreen != null) && (imgTurnHideFullScreenShown)){
                imgTurnHideFullScreen.setImageResource(R.drawable.ic_switch_on);
                imgTurnHideFullScreenShown = false
            }
            else{
                if (imgTurnHideFullScreen != null) imgTurnHideFullScreen.setImageResource(R.drawable.ic_switch_off);
                imgTurnHideFullScreenShown = true
            }
        }

        imgTurnHidelandscape.setOnClickListener {
            if ((imgTurnHidelandscape != null) && (imgTurnHidelandscapeShown)){
                imgTurnHidelandscape.setImageResource(R.drawable.ic_switch_on);
                imgTurnHidelandscapeShown = false
            }
            else{
                if (imgTurnHidelandscape != null) imgTurnHidelandscape.setImageResource(R.drawable.ic_switch_off);
                imgTurnHidelandscapeShown = true
            }
        }

        imgTurnHideKeyboardOpen.setOnClickListener {
            if ((imgTurnHideKeyboardOpen != null) && (imgTurnHideKeyboardOpenShown)){
                imgTurnHideKeyboardOpen.setImageResource(R.drawable.ic_switch_on);
                imgTurnHideKeyboardOpenShown = false
            }
            else{
                if (imgTurnHideKeyboardOpen != null) imgTurnHideKeyboardOpen.setImageResource(R.drawable.ic_switch_off);
                imgTurnHideKeyboardOpenShown = true
            }
        }
        imgTurnDisableHandle.setImageResource(R.drawable.ic_switch_on);

        imgTurnDisableHandle.setOnClickListener {
            if ((imgTurnDisableHandle != null) && (imgTurnDisableHandleShown)){
                imgTurnDisableHandle.setImageResource(R.drawable.ic_switch_on);
                imgTurnDisableHandleShown = false
            }
            else{
                if (imgTurnDisableHandle != null) imgTurnDisableHandle.setImageResource(R.drawable.ic_switch_off);
                imgTurnDisableHandleShown = true
            }
        }

        imgTurnDisableleftHandle.setOnClickListener {
            if ((imgTurnDisableleftHandle != null) && (imgTurnDisableleftHandleShown)){
                imgTurnDisableleftHandle.setImageResource(R.drawable.ic_switch_on);
                imgTurnDisableleftHandleShown = false
            }
            else{
                if (imgTurnDisableleftHandle != null) imgTurnDisableleftHandle.setImageResource(R.drawable.ic_switch_off);
                imgTurnDisableleftHandleShown = true
            }
        }

        imgTurnDisableRightHandle.setOnClickListener {
            if ((imgTurnDisableRightHandle != null) && (imgTurnDisableRightHandleShown)){
                imgTurnDisableRightHandle.setImageResource(R.drawable.ic_switch_on);
                imgTurnDisableRightHandleShown = false
            }
            else{
                if (imgTurnDisableRightHandle != null) imgTurnDisableRightHandle.setImageResource(R.drawable.ic_switch_off);
                imgTurnDisableRightHandleShown = true
            }
        }
    }
    @RequiresApi(Build.VERSION_CODES.O)
    private fun setDataSeekbar(){
        seekbar_handles.max=100
        seekbar_handles.min=0
        //set width,height,margin rl
        val params =
            rlBottomView.layoutParams as RelativeLayout.LayoutParams
        params.width=SharePref.getIntPref(applicationContext, "bottom_length")
        params.height=SharePref.getIntPref(applicationContext, "bottom_size")
        params.setMargins(SharePref.getIntPref(applicationContext, "bottom_position"),0,0,0)
        rlBottomView.layoutParams = params
        seekbar_handlesize.max=50
        seekbar_handlesize.min=0
        seekbar_handles.progress=SharePref.getIntPref(applicationContext, "progress_length")
        number_handles.text=(SharePref.getIntPref(applicationContext, "progress_length").toString())
        seekbar_handlesize.progress=SharePref.getIntPref(applicationContext, "progress_size")
        number_handlesize.setText(SharePref.getIntPref(applicationContext,"progress_size").toString())
        seekbar_handleposition.max=100
        seekbar_handleposition.min=0
        seekbar_handleposition.progress=SharePref.getIntPref(applicationContext, "progress_position")
        number_handle_position.setText(SharePref.getIntPref(applicationContext,"progress_position").toString())
        val metrics: DisplayMetrics =
            getResources().getDisplayMetrics()
        var widthred=metrics.widthPixels-rlBottomView.width;
        seekbar_handles.setOnSeekBarChangeListener(object :
            SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(
                seekBar: SeekBar,
                progress: Int,
                fromUser: Boolean
            ) {
                println("12323///"+progress)
                number_handles.setText("" + progress)
                val metrics: DisplayMetrics =
                    getResources().getDisplayMetrics()
                val params =
                    rlBottomView.layoutParams as RelativeLayout.LayoutParams
                val width = (metrics.widthPixels *progress/100).toInt()
//                params.width =
//                    5*progress
                params.width=width
                rlBottomView.layoutParams = params
                widthred=metrics.widthPixels-rlBottomView.width
                SharePref.setIntPref(applicationContext, "bottom_length", rlBottomView.width)
                SharePref.setIntPref(applicationContext, "progress_length", progress)
            }

            override fun onStartTrackingTouch(seekBar: SeekBar) {}
            override fun onStopTrackingTouch(seekBar: SeekBar) {}
        })
        seekbar_handlesize.setOnSeekBarChangeListener(object :
            SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(
                seekBar: SeekBar,
                progress: Int,
                fromUser: Boolean
            ) {
                println("12323///"+progress)
                number_handlesize.setText("" + progress)
                val params =
                    rlBottomView.layoutParams as RelativeLayout.LayoutParams
                //params.setMargins(80, 0, 0, 0) //left, top, right, bottom

                params.height =
                    5*progress
                rlBottomView.layoutParams = params
                SharePref.setIntPref(applicationContext, "bottom_size", rlBottomView.height)
                SharePref.setIntPref(applicationContext, "progress_size", progress)


            }

            override fun onStartTrackingTouch(seekBar: SeekBar) {}
            override fun onStopTrackingTouch(seekBar: SeekBar) {}
        })
        seekbar_handleposition.setOnSeekBarChangeListener(object :
            SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(
                seekBar: SeekBar,
                progress: Int,
                fromUser: Boolean
            ) {
                number_handle_position.setText("" + progress)
                SharePref.setIntPref(applicationContext, "bottom_position", progress)
                val params =
                    rlBottomView.layoutParams as RelativeLayout.LayoutParams
               //
                val metrics: DisplayMetrics =
                    getResources().getDisplayMetrics()
                var endprogress=0
                var isEnd=false
                    if (rlBottomView.right<(widthred)||isEnd==false){
                        isEnd=true
                        endprogress=progress
                        params.setMargins(widthred*progress/100, 0, 0, 0)
                    }else{
                        isEnd=false
                        params.setMargins(widthred*endprogress/100, 0, 0, 0)
                    }
                rlBottomView.layoutParams = params
                SharePref.setIntPref(applicationContext, "bottom_position", rlBottomView.marginLeft)
                SharePref.setIntPref(applicationContext, "progress_position", progress)
            }

            override fun onStartTrackingTouch(seekBar: SeekBar) {}
            override fun onStopTrackingTouch(seekBar: SeekBar) {}
        })

    }
    private fun Showdialog() {
        val dialog = Dialog(this)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.setContentView(R.layout.dialog_draw_handles)
        dialog.btn_setting.setOnClickListener {
            checkDrawOverlayPermission(baseContext)
//            dialog.dismiss()
        }
        dialog.btn_cancel.setOnClickListener {
            dialog.dismiss()
        }
        dialog.show()
    }

    val Overlay_REQUEST_CODE = 251
    fun checkDrawOverlayPermission(context: Context?) {
        if (Build.VERSION.SDK_INT >= 23) {
            if (!Settings.canDrawOverlays(context)) {
                val intent = Intent(
                    Settings.ACTION_MANAGE_OVERLAY_PERMISSION, Uri.parse(
                        "package:$packageName"
                    )
                )
                startActivityForResult(intent, Overlay_REQUEST_CODE)
            } else {
                PermissionRequired.youDesirePermissionCode1(this)
            }
        } else {
            PermissionRequired.youDesirePermissionCode1(this)
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_ACCESSIBILITY && resultCode == RESULT_OK) {
            checkDrawOverlayPermission(this)
        } else if (requestCode == PermissionRequired.Overlay_REQUEST_CODE) {
            if (Build.VERSION.SDK_INT >= 23) {
                if (Settings.canDrawOverlays(this)) {
                    openFloatingWindow(this)
                }
            } else {
                openFloatingWindow(this)
            }
        } else if (requestCode == 123 && Settings.System.canWrite(this)) {
            val intent = Intent(this, FloatingWindow::class.java)
            stopService(intent)
            startService(intent)
        }
    }
    private fun openFloatingWindow(c: Context) {
        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.WRITE_SETTINGS
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            c.startActivity(Intent(c, MainActivity::class.java))
        } else {
            askForPermission(Manifest.permission.WRITE_SETTINGS, 1)
        }
    }
    private fun askForPermission(permission: String, requestCode: Int) {
        if (ContextCompat.checkSelfPermission(
                this,
                permission
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, permission)) {
                ActivityCompat.requestPermissions(this, arrayOf(permission), requestCode)
            } else {
                Log.d("TAG", "askForPermission: $permission")
                ActivityCompat.requestPermissions(this, arrayOf(permission), requestCode)
            }
        } else {
            Toast.makeText(this, "$permission is already granted.", Toast.LENGTH_SHORT).show()
        }
    }
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String?>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == 123 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            startActivity(Intent(this, HandlesActivity::class.java))
        }
    }

    override fun onBackPressed() {
        val intent = Intent(this, MainActivity::class.java);
        startActivity(intent)
        finish()

    }
}


