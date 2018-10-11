package xiaoqiang.com.kotlintools.localstorage

import android.content.Context
import android.content.SharedPreferences

/**
 *   description: a SharedPreferences tool which can be used directly in a project
 *   created by crx on 2018/8/22 15:10
 */
object SpUtil {

    private var shareMap: MutableMap<String, SharedPreferences> = mutableMapOf()
    private var sp: SharedPreferences? = null

    fun instance(context: Context, spName: String = "sp_xml"): SpUtil{
        if(!shareMap.containsKey(spName)){
            val shareP = context.getSharedPreferences(spName, Context.MODE_PRIVATE)
            shareMap[spName] = shareP
            sp = shareP
        }else{
            sp = shareMap.get(spName)
        }
        return this
    }

    /**
     * save data
     */
    fun <T> put(key: String, value: T){
        when(value){
            is Int -> sp?.edit()?.putInt(key, value)?.apply()
            is String -> sp?.edit()?.putString(key, value)?.apply()
            is Boolean -> sp?.edit()?.putBoolean(key, value)?.apply()
            is Float -> sp?.edit()?.putFloat(key, value)?.apply()
            is Long -> sp?.edit()?.putLong(key, value)?.apply()
        }
    }

    /**
     * get data
     */
    @Suppress("IMPLICIT_CAST_TO_ANY","UNCHECKED_CAST")
    fun <T> get(key: String, default: T): T {
        val result = when(default){
            is Int -> sp?.getInt(key, default)
            is String -> sp?.getString(key, default)
            is Boolean -> sp?.getBoolean(key, default)
            is Float -> sp?.getFloat(key, default)
            is Long -> sp?.getLong(key, default)
            else -> throw IllegalArgumentException("SharedPreferences cannot get this type")
        }
        return result as T
    }
}

