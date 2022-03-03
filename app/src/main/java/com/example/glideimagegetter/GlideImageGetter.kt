package com.example.glideimagegetter

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.text.Html
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.Request
import com.bumptech.glide.request.target.SizeReadyCallback
import com.bumptech.glide.request.target.Target
import com.bumptech.glide.request.target.Target.SIZE_ORIGINAL
import com.bumptech.glide.request.transition.Transition
import java.lang.ref.WeakReference


class GlideImageGetter(
    textView: TextView,
    private val density: Float = textView.resources.displayMetrics.density
) : Html.ImageGetter {

    private val container: WeakReference<TextView> = WeakReference(textView)

    override fun getDrawable(source: String): Drawable {

        val drawable = BitmapDrawablePlaceholder()

        // Load Image to the Drawable
        container.get()?.apply {
            post {
                Glide.with(context)
                    .asBitmap()
                    .load(source)
                    .into(drawable)
            }
        }

        return drawable
    }

    private inner class BitmapDrawablePlaceholder : BitmapDrawable(
        container.get()?.resources,
        Bitmap.createBitmap(1, 1, Bitmap.Config.RGB_565)
    ), Target<Bitmap> {

        private var drawable: Drawable? = null
            set(value) {
                field = value
                value?.let { drawable ->
                    val drawableWidth = (drawable.intrinsicWidth / 3 * density).toInt()
                    val drawableHeight =
                        (drawable.intrinsicHeight / 3 * density).toInt()

                    val maxWidth = container.get()?.measuredWidth ?: 0

                    if (drawableWidth > maxWidth) {
                        val calculatedHeight = maxWidth * drawableHeight / drawableWidth
                        drawable.setBounds(0, 0, maxWidth, calculatedHeight)
                        setBounds(0, 0, maxWidth, calculatedHeight)
                    } else {
                        drawable.setBounds(0, 0, drawableWidth, drawableHeight)
                        setBounds(0, 0, drawableWidth, drawableHeight)
                    }
                    container.get()?.text = container.get()?.text
                }
            }

        override fun draw(canvas: Canvas) {
            drawable?.draw(canvas)
        }

        override fun onLoadStarted(placeholderDrawable: Drawable?) {
            placeholderDrawable?.let {
                drawable = it
            }
        }

        override fun onLoadFailed(errorDrawable: Drawable?) {
            errorDrawable?.let {
                drawable = it
            }
        }

        override fun onResourceReady(bitmap: Bitmap, transition: Transition<in Bitmap>?) {
            val transformedBitmap = Bitmap.createBitmap(
                bitmap.width,
                bitmap.height + (container.get()?.paint?.fontMetricsInt?.bottom ?: 0) * 2,
                Bitmap.Config.ARGB_8888
            )
            val canvas = Canvas(transformedBitmap)
            canvas.drawBitmap(
                bitmap,
                0f,
                container.get()?.paint?.fontMetricsInt?.bottom?.toFloat() ?: 0f,
                null
            )
            drawable = BitmapDrawable(
                container.get()?.resources ?: return,
                transformedBitmap
            )
        }

        override fun onLoadCleared(placeholderDrawable: Drawable?) {
            placeholderDrawable?.let {
                drawable = it
            }
        }

        override fun getSize(sizeReadyCallback: SizeReadyCallback) {
            sizeReadyCallback.onSizeReady(SIZE_ORIGINAL, SIZE_ORIGINAL)
        }

        override fun removeCallback(cb: SizeReadyCallback) {}
        override fun setRequest(request: Request?) {}
        override fun getRequest(): Request? {
            return null
        }

        override fun onStart() {}
        override fun onStop() {}
        override fun onDestroy() {}
    }
}