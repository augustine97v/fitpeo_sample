package com.fitpeo.sample.utils

import android.view.View
import android.view.Window
import android.view.WindowManager

object Utils {


    fun showProgress(view: View, window: Window, status: Boolean) {
        if (status) {
            window.setFlags(
                WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE
            )
            view.visibility = View.VISIBLE
        } else {
            window.clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
            view.visibility = View.GONE
        }
    }
}