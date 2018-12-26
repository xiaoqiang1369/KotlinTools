package xiaoqiang.com.kotlintools.extensions

import android.content.Context
import android.view.LayoutInflater

/**
 *   description: get Context's LayoutInflater
 *   created by crx on 2018/12/10 11:42
 */

val Context.layoutInflater: LayoutInflater get() = LayoutInflater.from(this)