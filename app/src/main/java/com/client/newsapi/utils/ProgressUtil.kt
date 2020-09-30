package com.client.newsapi.utils

import android.app.ProgressDialog
import android.content.Context
import android.util.Log

object ProgressUtil {
    private var mProgressDialog: ProgressDialog? = null
    private val mObject = Any()
    public fun startProgressDialog(context: Context?, message: String?) {
        try {
            synchronized(mObject) {
                if (mProgressDialog == null) {
                    mProgressDialog = ProgressDialog.show(context, "Syncing", message)
//                    mProgressDialog.setIndeterminate(true)
                }
            }
        } catch (e: Exception) {
            Log.e("Exception", "$e")
            e.printStackTrace()
        }
    }

    public fun stopProgressDialog(context: Context?) {
        try {
            synchronized(mObject) {
                if (mProgressDialog != null && mProgressDialog!!.isShowing) {
                    mProgressDialog!!.dismiss()
                    mProgressDialog = null
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}