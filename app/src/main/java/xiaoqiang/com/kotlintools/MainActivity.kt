package xiaoqiang.com.kotlintools

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import xiaoqiang.com.kotlintools.color.ImgColorFilter

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        ImgColorFilter(this).filterResColor(R.mipmap.ic_launcher, android.R.color.holo_blue_bright)

    }
}
