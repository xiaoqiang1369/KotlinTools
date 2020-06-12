package xiaoqiang.com.kotlintools

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.viewpager.widget.PagerAdapter
import android.view.View
import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.widget.ImageView
import android.widget.LinearLayout
import xiaoqiang.com.kotlintools.app.dpToPxR
import xiaoqiang.com.kotlintools.color.ImgColorFilter
import xiaoqiang.com.kotlintools.extensions.getViewModel
import xiaoqiang.com.kotlintools.image.loadImage
import xiaoqiang.com.kotlintools.view.BounceBackViewPager

class MainActivity : AppCompatActivity() {

    private lateinit var mViewPager: BounceBackViewPager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        ImgColorFilter(this).filterResColor(R.mipmap.ic_launcher, android.R.color.holo_blue_bright)

        mViewPager = findViewById(R.id.view_pager)
        mViewPager.apply {
            adapter = ViewPagerAdapter()
            offscreenPageLimit = 4
        }
    }

    class ViewPagerAdapter : PagerAdapter(){
        override fun isViewFromObject(p0: View, p1: Any): Boolean {
            return p0 == p1
        }

        override fun getCount(): Int {
            return 4
        }

        override fun instantiateItem(container: ViewGroup, position: Int): ImageView {
            val imageView = ImageView(container.context).apply {
                id = R.id.id_test
                background = ContextCompat.getDrawable(context, R.color.colorPrimary)
                layoutParams = ViewGroup.LayoutParams(MATCH_PARENT, WRAP_CONTENT)
            }
            loadImage(container.context, imageView, "https://wx2.sinaimg.cn/mw690/60d7cbf8gy1g0rybfdtprj20j10hy7qx.jpg")
            container.addView(imageView)
            return imageView
        }

        override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
            container.removeViewAt(position)
        }
    }
}
