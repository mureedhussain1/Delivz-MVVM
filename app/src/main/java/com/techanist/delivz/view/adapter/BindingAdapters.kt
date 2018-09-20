package com.techanist.delivz.view.adapter

import android.databinding.BindingAdapter
import android.widget.ImageView
import com.squareup.picasso.Picasso
import com.techanist.delivz.R

object BindingAdapters {

    @JvmStatic
    @BindingAdapter("loadImg")
    fun loadImg(imageView: ImageView, url: String?) {
        if (url != null && url.isNotEmpty())
            Picasso.get().load(url)
                    .placeholder(R.drawable.grey_placeholder)
                    .error(R.drawable.grey_placeholder).into(imageView)
        else
            Picasso.get().load(R.drawable.grey_placeholder).into(imageView)
    }
}