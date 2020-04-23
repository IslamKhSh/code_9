package com.devopsolution.code9.common.extensions

import android.annotation.SuppressLint
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.ColorRes
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import com.devopsolution.code9.Code9App
import com.devopsolution.code9.common.Constants
import com.google.android.material.snackbar.Snackbar


fun Fragment.toast(message: CharSequence, duration: Int = Toast.LENGTH_SHORT) = activity?.toast(message, duration)

fun Fragment.toast(@StringRes strResource: Int, duration: Int = Toast.LENGTH_SHORT) =
    activity?.toast(resources.getString(strResource), duration)

fun Fragment.getColorCompat(@ColorRes id: Int) = context!!.getColorCompat(id)

fun Fragment.goToActivity(activityClass: Class<*>) = activity?.goToActivity(activityClass)

fun Fragment.appComponent() = (activity?.application!! as Code9App).component

@SuppressLint("WrongConstant")
fun Fragment.errorMsg(msg: String, duration: Int = Constants.SNAK_BAR_DURATION) {

    view?.run {
        val snackbar = Snackbar.make(this, msg, duration)
        snackbar.view.setBackgroundColor(getColorCompat(android.R.color.holo_red_dark)!!)
        val textView = snackbar.view.findViewById(com.google.android.material.R.id.snackbar_text) as TextView
        textView.setTextColor(getColorCompat(android.R.color.white)!!)
        snackbar.show()
    }
}


fun Fragment.errorMsg(@StringRes msgId: Int, duration: Int = Constants.SNAK_BAR_DURATION) {
    errorMsg(getString(msgId), duration)
}


@SuppressLint("WrongConstant")
fun Fragment.warningMsg(msg: String, duration: Int = Constants.SNAK_BAR_DURATION) {

    view?.run {
        val snackbar = Snackbar.make(this, msg, duration)

        snackbar.view.setBackgroundColor(getColorCompat(android.R.color.holo_orange_light))
        val textView = snackbar.view.findViewById(com.google.android.material.R.id.snackbar_text) as TextView
        textView.setTextColor(getColorCompat(android.R.color.black))
        snackbar.show()
    }

}

fun Fragment.warningMsg(@StringRes msgId: Int, duration: Int = Constants.SNAK_BAR_DURATION) {
    warningMsg(getString(msgId), duration)
}


@SuppressLint("WrongConstant")
fun Fragment.confirmMsg(msg: String, duration: Int = Constants.SNAK_BAR_DURATION) {

    view?.run {
        val snackbar = Snackbar.make(this, msg, duration)

        snackbar.view.setBackgroundColor(getColorCompat(android.R.color.holo_green_dark))

        val textView = snackbar.view.findViewById(com.google.android.material.R.id.snackbar_text) as TextView
        textView.setTextColor(getColorCompat(android.R.color.white))
        snackbar.show()
    }
}

fun Fragment.confirmMsg(@StringRes msgId: Int, duration: Int = Constants.SNAK_BAR_DURATION) {
    confirmMsg(getString(msgId), duration)
}
