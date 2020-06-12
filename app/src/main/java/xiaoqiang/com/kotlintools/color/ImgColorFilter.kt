package xiaoqiang.com.kotlintools.color

import android.content.Context
import android.graphics.PorterDuff
import android.graphics.drawable.Drawable
import androidx.annotation.ColorInt
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.core.content.ContextCompat

/**
 *   description: set color for a image or change its original color
 *   return its drawable
 *   created by crx on 2018/8/22 16:35
 */
class ImgColorFilter(private val context: Context) {


    /**
     * @param img Drawable image or just its resId
     * @param color color to set to the image
     * @return Drawable image
     */
    fun <T> filterColor(img: T?, @ColorInt color: Int): Drawable?{
        var drawable: Drawable? = null
        when(img){
            is Drawable -> drawable = img
            is Int -> drawable = getImgDrawable(img)
        }
        drawable?.setColorFilter(color, PorterDuff.Mode.SRC_ATOP)
        return drawable
    }

    /**
     * @param img Drawable image or just its resId
     * @param color color resId
     * @return Drawable image
     */
    fun <T> filterResColor(img: T?, @ColorRes colorId: Int): Drawable?{
        var drawable: Drawable? = null
        when(img){
            is Drawable -> drawable = img
            is Int -> drawable = getImgDrawable(img)
        }
        drawable?.setColorFilter(getColor(colorId), PorterDuff.Mode.SRC_ATOP)
        return drawable
    }


    private fun getColor(@ColorRes colorId: Int) = ContextCompat.getColor(context, colorId)
    private fun getImgDrawable(@DrawableRes resId: Int) = ContextCompat.getDrawable(context, resId)


}