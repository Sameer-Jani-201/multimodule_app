package com.bookmyshow.feature_one.adapter

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bookmyshow.core.ImageLoader

@BindingAdapter("imageUrl", "loader")
fun loadImage(view: ImageView, url: String, loader: ImageLoader) {
    loader.loadImage(
        url, view
    )
}