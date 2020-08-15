package com.omkar.cavistademo.databinding

import androidx.appcompat.widget.AppCompatImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.omkar.cavistademo.R


/**
 *   bind image using glide library to the image list item
 */
@BindingAdapter("app:image")

fun bindArticleImage(
    imageView: AppCompatImageView,
    url: String?
) {
    if (url != null && !url.endsWith("/")) {
        Glide.with(imageView.context).load(url)
            .apply(
                RequestOptions()
                    .diskCacheStrategy(DiskCacheStrategy.ALL).placeholder(R.drawable.icn_loading)
            ).into(imageView)
    } else {
        Glide.with(imageView.context).clear(imageView)
    }
}
