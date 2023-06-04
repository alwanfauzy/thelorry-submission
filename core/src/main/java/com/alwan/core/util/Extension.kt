package com.alwan.core.util

import android.content.Context
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import com.alwan.core.R
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestBuilder
import com.bumptech.glide.load.engine.DiskCacheStrategy

fun ImageView.loadImage(url: String) {
    Glide.with(this)
        .load(url)
        .override(400)
        .error(R.color.gray_darker)
        .placeholder(R.color.gray_darker)
        .into(this)
}

fun Int?.orZero() = this ?: 0

fun View.show() {
    this.visibility = View.VISIBLE
}

fun View.hide() {
    this.visibility = View.GONE
}

fun Context.showToast(message: String?) = Toast.makeText(this, message, Toast.LENGTH_SHORT).show()