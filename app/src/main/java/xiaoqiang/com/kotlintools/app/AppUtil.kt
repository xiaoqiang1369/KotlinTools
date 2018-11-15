package xiaoqiang.com.kotlintools.app

import android.app.Activity
import android.content.Context
import android.content.ContextWrapper

/**
 *   description: methods relative to application
 *   created by crx on 2018/8/20 17:26
 */


/**
 * get App version name
 */
fun getVersionName(context: Context): String = try {
    val packageManager = context.packageManager
    val packInfo = packageManager.getPackageInfo(context.packageName, 0)
    packInfo.versionName
} catch (e: Exception) {
    e.printStackTrace()
    ""
}

/**
 * get activity by context
 */
fun getActivityContext(context: Context): Activity? {
    var activityContext = context
    while (activityContext is ContextWrapper) {
        if (activityContext is Activity) {
            return activityContext
        }
        activityContext = activityContext.baseContext
    }
    return null
}