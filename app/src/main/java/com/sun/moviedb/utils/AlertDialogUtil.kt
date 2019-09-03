package com.sun.moviedb.utils

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import com.sun.moviedb.R

object AlertDialogUtil {
    @SuppressLint("InflateParams", "SetTextI18n")
    fun showAppInfoMessageDialog(
            context: Context,
            cancelable: Boolean
    ) {
        val view = LayoutInflater.from(context).inflate(R.layout.dialog_app_info, null)
        val tvVersion = view.findViewById<TextView>(R.id.tvVersion)
        val tvBuildTime = view.findViewById<TextView>(R.id.tvBuildTime)
        val tvWebsite = view.findViewById<TextView>(R.id.tvWebsite)
        val tvEmail = view.findViewById<TextView>(R.id.tvEmail)

        tvVersion.text = "Version: ${DeviceUtil.getVersionApp()}(${DeviceUtil.getVersionData()})"
        tvBuildTime.text ="Build: ${DeviceUtil.getBuildTime(context.getString(R.string.date_time12))}"
        tvWebsite.text = "Website: " + context.getString(R.string.link_website)
        tvEmail.text = "Email: " + context.getString(R.string.email)
        val width = context.resources.displayMetrics.widthPixels * 95 / 100
        val dialog = createDialog(context, cancelable, view)
        dialog.show()
        dialog.window.attributes.width = width
        dialog.setCancelable(cancelable)
        dialog.window.setGravity(Gravity.CENTER)
    }

    fun createDialog(context: Context, cancelable: Boolean, view: View): Dialog {
        val dialog = AlertDialog.Builder(context)
                .setCancelable(cancelable)
                .setView(view)
                .create()
        dialog.window?.decorView?.setBackgroundResource(android.R.color.transparent)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.window?.setBackgroundDrawableResource(android.R.color.transparent)

        return dialog
    }

}