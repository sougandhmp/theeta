package com.android.theta.commons

import android.graphics.drawable.Drawable
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

@BindingAdapter("srcUrl", "circleCrop", "placeholder", "loadListener", requireAll = false)
fun ImageView.bindSrcUrl(
    url: String?,
    circleCrop: Boolean,
    placeholder: Drawable?,
    loadListener: GlideImageLoadListener?
) {
    val request = Glide.with(this).load(url)
    if (circleCrop) request.circleCrop()
    if (placeholder != null) request.placeholder(placeholder)
    if (loadListener != null) request.listener(loadListener)
    request.into(this)
}
