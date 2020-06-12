package xiaoqiang.com.kotlintools.extensions

import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.GradientDrawable
import android.support.annotation.ColorInt
import android.view.View
import android.view.ViewGroup

/**
 * created by crx in 2020/5/18 11:51
 * description: extension functions of class View
 */

/**
 * set corner radius, use default background color
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

/**
 * set corner radius and background color
 */
fun View.setCorner(@ColorInt bgColor: Int, radius: Float){
    val gd = GradientDrawable()
    gd.setColor(bgColor)
    gd.cornerRadius = radius
    background = gd
}

/**
 * set just stroke(color&width)
 */
fun View.setStroke(@ColorInt color: Int, strokeWidth: Int) {
    val gd = GradientDrawable()
    val bgColor = if (background is ColorDrawable) (background as ColorDrawable).color else 0
    if (bgColor != 0) {
        gd.setColor(bgColor)
    }
    gd.setStroke(strokeWidth, color)
    background = gd;
}

/**
 * set stroke(color, width) & corner radius & background color
 */
fun View.setStroke(@ColorInt strokeColor: Int, strokeWidth: Int, @ColorInt bgColor: Int, radius: Float = 0f) {
    val gd = GradientDrawable()
    gd.setColor(bgColor)
    gd.setStroke(strokeWidth, strokeColor)
    gd.cornerRadius = radius;
    background = gd;
}

