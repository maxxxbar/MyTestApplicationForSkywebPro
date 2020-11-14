package com.worldshine.mytestapplicationforskywebpro.utils

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.google.android.material.snackbar.Snackbar
import com.worldshine.mytestapplicationforskywebpro.R

fun ImageView.loadImageWithGlide(imgUrl: String) {
    Glide.with(this)
        .load(imgUrl)
        .placeholder(R.drawable.ic_placeholder)
        .error(R.drawable.ic_placeholder)
        .into(this)
}

fun createSnackbar(view: View, text: String, length: Int = Snackbar.LENGTH_LONG) {
    val sb = Snackbar.make(view, text, length)
    val viewSb = sb.view
    val tv: TextView = viewSb.findViewById(com.google.android.material.R.id.snackbar_text)
    tv.maxLines = 10
    sb.show()
}

fun String.isValidEmail(): Boolean {
    val reg =
        Regex(pattern = "[a-z0-9!#\$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#\$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?")
    return reg.matches(this)
}

fun String.isValidPassword(): Boolean {
    val reg = Regex(pattern = "^(?=.*\\d)(?=.*[a-zа-яё])(?=.*[A-ZА-ЯЁ])(?=.*[A-ZА-ЯЁ]).{6,}\$")
    return reg.matches(this)
}