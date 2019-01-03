package xiaoqiang.com.kotlintools.app

import android.content.res.Resources

/**
 *   description: functions related to dimension
 *   created by crx on 2018/12/28 14:36
 */

private fun getDensity() = Resources.getSystem().displayMetrics.density

fun dpToPx(dp: Int): Float = getDensity() * dp

fun pxToDp(px: Float): Float = px / getDensity()

/**
 * dp to px
 * @return the rounding pixel Int size
 */
fun dpToPxR(dp: Int): Int = (getDensity() * dp + 0.5f).toInt()

/**
 * px to dp
 * @return the rounding dp Int size
 */
fun pxToDpR(px: Float): Int = (px / getDensity() + 0.5f).toInt()


