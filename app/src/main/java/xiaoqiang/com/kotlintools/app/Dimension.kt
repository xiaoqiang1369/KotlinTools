package xiaoqiang.com.kotlintools.app

import android.content.res.Resources
import android.util.TypedValue

/**
 *   description: functions related to dimension
 *   created by crx on 2018/12/28 14:36
 */


fun dpToPx(dp: Float): Float = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, Resources.getSystem().displayMetrics)


fun pxToDp(px: Float): Float = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, px, Resources.getSystem().displayMetrics)



