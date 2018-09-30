package xiaoqiang.com.kotlintools.color

import android.content.Context
import android.graphics.PorterDuff
import android.graphics.drawable.Drawable
import android.support.annotation.ColorInt
import android.support.annotation.ColorRes
import android.support.annotation.DrawableRes
import android.support.v4.content.ContextCompat

/**
 *   description: 给图片着色的工具
 *   created by crx on 2018/8/22 16:35
 */
class ImgColorFilter(private val context: Context) {


    /**
     * 给图片着色
     * @param img 要着色的图片，可以传入Drawable格式或其资源id
     * @param color 颜色
     * @return 返回Drawable格式的图片
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
     * 给图片着色
     * @param img 要着色的图片，可以传入Drawable格式或其资源id
     * @param colorId 颜色资源id
     * @return 返回Drawable格式的图片
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