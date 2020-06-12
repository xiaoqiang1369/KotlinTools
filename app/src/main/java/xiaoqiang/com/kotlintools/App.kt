package xiaoqiang.com.kotlintools

import android.app.Application
import android.content.Context

/**
 * created by crx in 2020/6/12 11:13
 * description:
 */
class App : Application(){


    companion object{
       lateinit var instance : App
            private set
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }
}