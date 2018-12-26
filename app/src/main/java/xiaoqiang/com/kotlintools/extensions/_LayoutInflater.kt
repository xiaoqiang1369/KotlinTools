package xiaoqiang.com.kotlintools.extensions

import android.content.Context
import android.support.annotation.LayoutRes
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

/**
 *   description: get Context's LayoutInflater
 *   created by crx on 2018/12/10 11:42
 */

val Context.layoutInflater: LayoutInflater get() = LayoutInflater.from(this)

fun Context.inflate(@LayoutRes resource: Int, root: ViewGroup?, attachToRoot: Boolean = false): View {
    return LayoutInflater.from(this).inflate(resource, root, attachToRoot)
}