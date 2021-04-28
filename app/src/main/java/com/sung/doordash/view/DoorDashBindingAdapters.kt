package com.sung.doordash.view

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.squareup.picasso.Picasso

object DoorDashBindingAdapters {
    @JvmStatic
    @BindingAdapter("url")
    fun setImageResource(imageView: ImageView, url: String) {
        Picasso.get().load(url).into(imageView)
    }
}