package xiaoqiang.com.kotlintools.app

import android.app.Activity
import android.content.Context
import android.content.ContextWrapper

/**
 *   description: App相关的工具方法类
 *   created by crx on 2018/8/20 17:26
 */
class AppUtil {

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

    companion object {
        /**
         * 通过context获取其对应的Activity
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
    }
}