package com.client.newsapi.receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.View
import android.widget.RelativeLayout
import com.google.android.material.snackbar.Snackbar
import com.client.newsapi.utils.NetworkUtil


public class ConnectivityChangeReceiver : BroadcastReceiver() {

    var mContext: Context? = null
//    var mActivity: Activity? = null

    override fun onReceive(context: Context?, intent: Intent?) {
        mContext = context
//        mActivity = mContext as Activity?

        val status = mContext?.let { NetworkUtil.getConnectivityStatusString(it) }
        Log.e("Receiver ", "" + status)
//        val rootView: View = (mActivity)!!.window.decorView.findViewById(R.id.content)
        val relativeLayout: RelativeLayout
        val rLParams: RelativeLayout.LayoutParams = RelativeLayout.LayoutParams(
            RelativeLayout.LayoutParams.FILL_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT
        )
        rLParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM, 1)
//        re
        if (status == "Not connected to Internet") {
            Log.e("Receiver ", "not connected")
//            Snackbar.make(rLParams, "No internet", Snackbar.LENGTH_INDEFINITE).setAction("Close",
//                View.OnClickListener { }).show()
        } else {
            Log.e("Receiver ", "connected")
//            Snackbar.make(mContext, "Connected to internet", Snackbar.LENGTH_SHORT).show()

        }
    }
}