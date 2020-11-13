package com.worldshine.mytestapplicationforskywebpro.utils

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.worldshine.mytestapplicationforskywebpro.R

fun ImageView.loadImageWithGlide(imgUrl: String) {
    Glide.with(this)
        .load(imgUrl)
        .placeholder(R.drawable.ic_placeholder)
        .error(R.drawable.ic_placeholder)
        .into(this)
}