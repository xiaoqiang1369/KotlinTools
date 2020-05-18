package xiaoqiang.com.kotlintools.extensions

import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.GradientDrawable
import android.support.annotation.ColorInt
import android.view.View
import android.view.ViewGroup

/**
 * created by crx in 2020/5/18 11:51
 * description: set corner and stroke for View & ViewGroup
 */

fun View.setCorner(radius: Float) {
    val gd = GradientDrawable()
    val bgColor = if (background is ColorDrawable) (background as ColorDrawable).color else 0
    if (bgColor != 0) {
        gd.setColor(bgColor)
    }
    gd.cornerRadius = radius
    background = gd
}

fun View.setStroke(@ColorInt color: Int, strokeWidth: Int) {
    val gd = GradientDrawable()
    val bgColor = if (background is ColorDrawable) (background as ColorDrawable).color else 0
    if (bgColor != 0) {
        gd.setColor(bgColor)
    }
    gd.setStroke(strokeWidth, color)
    background = gd;
}

fun ViewGroup.setCorner(radius: Float) {
    val gd = GradientDrawable()
    val bgColor = if (background is ColorDrawable) (background as ColorDrawable).color else 0
    if (bgColor != 0) {
        gd.setColor(bgColor)
    }
    gd.cornerRadius = radius
    background = gd
}

fun ViewGroup.setStroke(@ColorInt color: Int, strokeWidth: Int) {
    val gd = GradientDrawable()
    val bgColor = if (background is ColorDrawable) (background as ColorDrawable).color else 0
    if (bgColor != 0) {
        gd.setColor(bgColor)
    }
    gd.setStroke(strokeWidth, color)
    background = gd;
}

