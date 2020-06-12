package xiaoqiang.com.kotlintools.extensions

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner

/**
 * created by crx in 2020/6/12 12:52
 * description: extensions functions for ViewModel
 */

fun <T : ViewModel> ViewModelStoreOwner.getViewModel(clazz: Class<T>) = ViewModelProvider(this).get(clazz)