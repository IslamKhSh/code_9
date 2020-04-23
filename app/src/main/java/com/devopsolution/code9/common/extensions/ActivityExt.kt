package com.devopsolution.code9.common.extensions

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.StringRes
import com.devopsolution.code9.Code9App
import com.devopsolution.code9.common.Constants
import com.google.android.material.snackbar.Snackbar


fun Activity.toast(message: CharSequence, duration: Int = Toast.LENGTH_SHORT) =
    Toast.makeText(this, message, duration).show()


fun Activity.goToActivity(activityClass: Class<*>) = this.startActivity(Intent(this, activityClass))


fun Activity.appComponent() = (this.application as Code9App).component


@SuppressLint("WrongConstant")
fun Activity.errorMsg(msg: String, duration: Int = Constants.SNAK_BAR_DURATION) {

    val snackbar = Snackbar.make(this.window.decorView, msg, duration)
    snackbar.view.setBackgroundColor(getColorCompat(android.R.color.holo_red_dark))
    val textView = snackbar.view.findViewById(com.google.android.material.R.id.snackbar_text) as TextView
    textView.setTextColor(getColorCompat(android.R.color.white))
    snackbar.show()

}

fun Activity.errorMsg(@StringRes msgId: Int, duration: Int = Constants.SNAK_BAR_DURATION) {
    errorMsg(getString(msgId), duration)
}
