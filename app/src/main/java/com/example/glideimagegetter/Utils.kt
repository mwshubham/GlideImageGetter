package com.example.glideimagegetter

import android.content.res.Resources
import android.os.Build
import android.text.Html
import android.text.Spannable
import android.widget.TextView

fun TextView.setTextFromHtml(
    message: String,
    imageGetter: Html.ImageGetter = GlideImageGetter(this),
    tagHandler: Html.TagHandler? = null
) {
    this.text = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
        Html.fromHtml(message, Html.FROM_HTML_MODE_LEGACY, imageGetter, tagHandler) as Spannable
    } else {
        @Suppress("DEPRECATION")
        Html.fromHtml(message, imageGetter, tagHandler) as Spannable
    }
}

fun Int.dpToPx() = convertDpToPixel(toFloat()).toInt()

fun convertDpToPixel(dp: Float): Float {
    val metrics = Resources.getSystem().displayMetrics
    val px = dp * (metrics.densityDpi / 160f)
    return Math.round(px).toFloat()
}
