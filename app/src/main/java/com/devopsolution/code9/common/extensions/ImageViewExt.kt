package com.devopsolution.code9.common.extensions

import android.graphics.drawable.Drawable
import android.net.Uri

import android.widget.ImageView
import androidx.annotation.DrawableRes
import androidx.annotation.Nullable
import androidx.databinding.BindingAdapter
import com.devopsolution.code9.common.GlideApp

import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.devopsolution.code9.BuildConfig
import java.io.File

fun ImageView.loadImg(
    @Nullable photo: String, @DrawableRes placeHolder: Int,
    @DrawableRes error: Int = placeHolder
) = loadImg(Uri.parse(photo), placeHolder, error)

fun ImageView.loadImg(
    @Nullable photo: Uri, @DrawableRes placeHolder: Int,
    @DrawableRes error: Int = placeHolder
) {

    val myOptions = RequestOptions()
        .override(this.width, this.height)
        .fitCenter()

    GlideApp.with(context!!)
        .load(photo)
        .placeholder(placeHolder)
        .error(error)
        .fallback(error)
        .apply(myOptions)
        .diskCacheStrategy(DiskCacheStrategy.ALL)
        .into(this)
}

@BindingAdapter(value = ["imgUrl", "placeHolder"], requireAll = false)
fun ImageView.loadImg(
    @Nullable photo: String?, placeHolder: Drawable?
) {

    GlideApp.with(context!!)
        .asBitmap()
        .load(BuildConfig.BASE_URL + photo)
        .placeholder(placeHolder)
        .error(placeHolder)
        .fallback(placeHolder)
        .diskCacheStrategy(DiskCacheStrategy.ALL)
        .into(this)
}

fun ImageView.loadImg(
    @Nullable photo: File, @DrawableRes placeHolder: Int,
    @DrawableRes error: Int = placeHolder
) {

    val myOptions = RequestOptions()
        .override(this.width, this.height)
        .fitCenter()


    GlideApp.with(context!!)
        .load(photo)
        .placeholder(placeHolder)
        .error(error)
        .fallback(error)
        .apply(myOptions)
        .diskCacheStrategy(DiskCacheStrategy.ALL)
        .into(this)
}

