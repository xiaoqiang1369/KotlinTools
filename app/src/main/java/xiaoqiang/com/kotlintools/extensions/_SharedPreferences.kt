package xiaoqiang.com.kotlintools.extensions

import android.content.SharedPreferences

/**
 *   created by crx on 2018/8/22 15:10
 *   description: extension functions of class SharedPreferences
 */


/**
 * save data
 */
fun <T> SharedPreferences.put(key: String, value: T) {
    when (value) {
        is Int -> edit().putInt(key, value)?.apply()
        is String -> edit().putString(key, value)?.apply()
        is Boolean -> edit().putBoolean(key, value)?.apply()
        is Float -> edit().putFloat(key, value)?.apply()
        is Long -> edit().putLong(key, value)?.apply()
    }
}

/**
 * get data
 */
@Suppress("IMPLICIT_CAST_TO_ANY", "UNCHECKED_CAST")
fun <T> SharedPreferences.get(key: String, default: T): T {
    val result = when (default) {
        is Int -> getInt(key, default)
        is String -> getString(key, default)
        is Boolean -> getBoolean(key, default)
        is Float -> getFloat(key, default)
        is Long -> getLong(key, default)
        else -> throw IllegalArgumentException("SharedPreferences cannot get this type")
    }
    return result as T
}


