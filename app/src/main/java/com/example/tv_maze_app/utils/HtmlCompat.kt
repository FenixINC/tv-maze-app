package com.example.tv_maze_app.utils

import android.content.Intent
import android.net.Uri
import android.text.Html
import android.text.Spanned

object HtmlCompat {
    fun fromHtml(html: String): Spanned {
        val result: Spanned
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            result = Html.fromHtml(html, Html.FROM_HTML_MODE_LEGACY)
        } else {
            result = Html.fromHtml(html)
        }
        return result
    }

    fun fromHtml(html: String, tagHandler: Html.TagHandler): Spanned {
        val result: Spanned
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            val intent = Intent()
            intent.data = Uri.parse(html)
            result = Html.fromHtml(html, Html.FROM_HTML_MODE_LEGACY, null, tagHandler)
        } else {
            result = Html.fromHtml(html, null, tagHandler)
        }
        return result
    }
}