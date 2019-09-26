package com.example.tv_maze_app.presentation.adapters

import android.text.TextUtils
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.NonNull
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.example.tv_maze_app.R
import com.example.tv_maze_app.data.models.Cast
import com.example.tv_maze_app.data.models.Crew
import com.example.tv_maze_app.data.models.TvShow
import com.example.tv_maze_app.utils.HtmlCompat
import timber.log.Timber

object BindingAdapterHelper {

    @JvmStatic
    @BindingAdapter("bindImage")
    fun setImageMedium(@NonNull imageView: ImageView, @NonNull tvShow: TvShow) {
        if (TextUtils.isEmpty(tvShow.image?.medium)) {
            Glide.with(imageView.context)
                    .load("")
                    .placeholder(R.color.grey)
                    .error(R.color.grey)
                    .into(imageView)
            Timber.w("Not preview image for tvShow: %s", tvShow.toString())
            return
        }
        Glide.with(imageView.context)
                .load(tvShow.image?.medium)
                .placeholder(R.color.grey)
                .error(R.color.grey)
                .into(imageView)
    }

    @JvmStatic
    @BindingAdapter("bindImageLarge")
    fun setImageOriginal(@NonNull imageView: ImageView, @NonNull tvShow: TvShow) {
        if (TextUtils.isEmpty(tvShow.image?.original)) {
            Glide.with(imageView.context)
                    .load("")
                    .placeholder(R.color.grey)
                    .error(R.color.grey)
                    .into(imageView)
            Timber.w("Not preview image for tvShow: %s", tvShow.toString())
            return
        }
        Glide.with(imageView.context)
                .load(tvShow.image?.original)
                .placeholder(R.color.grey)
                .error(R.color.grey)
                .into(imageView)
    }

    @JvmStatic
    @BindingAdapter("bindImage")
    fun setImageMedium(@NonNull imageView: ImageView, @NonNull cast: Cast) {
        if (TextUtils.isEmpty(cast.person?.image?.medium)) {
            Glide.with(imageView.context)
                    .load("")
                    .placeholder(R.color.grey)
                    .error(R.color.grey)
                    .into(imageView)
            Timber.w("Not preview image for tvShow: %s", cast.toString())
            return
        }
        Glide.with(imageView.context)
                .load(cast.person?.image?.medium)
                .placeholder(R.color.grey)
                .error(R.color.grey)
                .into(imageView)
    }

    @JvmStatic
    @BindingAdapter("bindImage")
    fun setImageMedium(@NonNull imageView: ImageView, @NonNull crew: Crew) {
        if (TextUtils.isEmpty(crew.person?.image?.medium)) {
            Glide.with(imageView.context)
                    .load("")
                    .placeholder(R.color.grey)
                    .error(R.color.grey)
                    .into(imageView)
            Timber.w("Not preview image for tvShow: %s", crew.toString())
            return
        }
        Glide.with(imageView.context)
                .load(crew.person?.image?.medium)
                .placeholder(R.color.grey)
                .error(R.color.grey)
                .into(imageView)
    }

    @JvmStatic
    @BindingAdapter("bindText")
    fun setText(@NonNull textView: TextView, text: String) {
        if (!TextUtils.isEmpty(text))
            textView.text = HtmlCompat.fromHtml(text)
        else
            textView.visibility = View.GONE
    }

    @JvmStatic
    @BindingAdapter("bindOffWebsite")
    fun setOffWebsiteText(@NonNull textView: TextView, model: TvShow) {
        if (!TextUtils.isEmpty(model.officialSite))
            textView.text = model.officialSite
        else
            textView.visibility = View.GONE
    }
}