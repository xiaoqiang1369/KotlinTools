package xiaoqiang.com.kotlintools.localstorage

import android.content.Context
import android.content.SharedPreferences
import xiaoqiang.com.kotlintools.App
import xiaoqiang.com.kotlintools.extensions.get
import xiaoqiang.com.kotlintools.extensions.put

/**
 * created by crx in 2020/6/12 10:54
 * description: base class for SharedPreferences
 */

public abstract class BaseSp(val spName: String){

    private val sharedPre: SharedPreferences = App.instance.getSharedPreferences(spName, Context.MODE_PRIVATE)

    protected fun <T> put(key: String, t: T) {
        sharedPre.put(key, t)
    }

    protected fun <T> get(key: String, default: T) = sharedPre.get(key, default)

}