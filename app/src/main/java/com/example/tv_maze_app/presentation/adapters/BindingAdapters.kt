package com.example.tv_maze_app.presentation.adapters

import android.text.TextUtils
import android.widget.ImageView
import androidx.annotation.NonNull
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.example.tv_maze_app.R
import com.example.tv_maze_app.data.models.TvShow
import timber.log.Timber

class BindingAdapters {
    companion object {
        @JvmStatic
        @BindingAdapter("bindImage")
        fun setImageCategory(@NonNull imageView: ImageView, @NonNull tvShow: TvShow) {
            if (TextUtils.isEmpty(tvShow.image.medium)) {
                Glide.with(imageView.context)
                        .load("")
                        .placeholder(R.color.grey)
                        .error(R.color.grey)
                        .into(imageView)
                Timber.w("Not preview image for tvShow: %s", tvShow.toString())
                return
            }
            Glide.with(imageView.context)
                    .load(tvShow.image.medium)
                    .placeholder(R.color.grey)
                    .error(R.color.grey)
                    .into(imageView)
        }
    }
}