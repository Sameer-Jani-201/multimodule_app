package com.bookmyshow.feature_two.adapter

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.bookmyshow.core.ImageLoader
import com.bookmyshow.domain_layer.model.VenuesModel

@BindingAdapter("dates")
fun showTimes(view: TextView, list: List<VenuesModel.ShowTime>?) {
    var times = ""

    list?.forEach {
        times += "Show Time : ${it.showTime}"
        times += "\n"
        times += "DateCode : ${it.showDateCode}"
        times += "\n\n"
    }
    view.text = times
}

@BindingAdapter("imageUrl", "loader")
fun loadImage(view: ImageView, url: String, loader: ImageLoader) {
    loader.loadImage(
        url, view
    )
}