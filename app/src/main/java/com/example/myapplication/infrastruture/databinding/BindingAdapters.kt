package com.example.myapplication.infrastruture.databinding

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions


@BindingAdapter(value = ["imageBitmapUrl"])
fun loadImage(imageView: ImageView, url: String) {
    if (url.isEmpty()) return
    try {
        Glide.with(imageView)
            .load(url)
            .transition(DrawableTransitionOptions.withCrossFade(300))
            .diskCacheStrategy(DiskCacheStrategy.NONE)
            .into(imageView)
    } catch (e: Exception) {
        e.printStackTrace()
    }
}