package com.client.newsapi.utils

import android.view.View

interface OnClickListener {
    fun onClick(view: View?, position: Int)
    fun onLongClick(view: View?, position: Int)
}