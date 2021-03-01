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
import android.util.Log
import android.view.View
import android.view.Window
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.miui_ify.FloatingWindow
import com.example.miui_ify.MainActivity
import com.example.settingapp.R
import com.example.settingapp.required.PermissionRequired
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
    private var imgTurnDisableHandleShown = true
    private var imgTurnDisableleftHandleShown = true
    private var imgTurnDisableRightHandleShown = true
    private val REQUEST_ACCESSIBILITY = 777

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_handles)
        val window: Window = this.getWindow()
        getWindow().decorView.systemUiVisibility =
            View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR //  set status text dark

        window.statusBarColor = ContextCompat.getColor(this, R.color.white)

        Showdialog()

        imgTurnFullleght.setOnClickListener {
            if ((imgTurnFullleght != null) && (imgTurnFullleghtShown)){
                imgTurnFullleght.setImageResource(R.drawable.ic_switch_on);
                imgTurnFullleghtShown = false
            }
            else{
                if (imgTurnFullleght != null) imgTurnFullleght.setImageResource(R.drawable.ic_switch_off);
                imgTurnFullleghtShown = true
            }
        }

        imgTurnHideHandle.setOnClickListener {
            if ((imgTurnHideHandle != null) && (imgTurnHideHandleShown)){
                imgTurnHideHandle.setImageResource(R.drawable.ic_switch_on);
                imgTurnHideHandleShown = false
            }
            else{
                if (imgTurnHideHandle != null) imgTurnHideHandle.setImageResource(R.drawable.ic_switch_off);
                imgTurnHideHandleShown = true
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
        imgBack.setOnClickListener {
          onBackPressed()
        }
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


