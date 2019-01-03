package xiaoqiang.com.kotlintools

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.constraint.ConstraintLayout
import android.support.v4.content.ContextCompat
import android.support.v4.view.PagerAdapter
import android.view.View
import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.widget.ImageView
import android.widget.LinearLayout
import xiaoqiang.com.kotlintools.app.dpToPxR
import xiaoqiang.com.kotlintools.color.ImgColorFilter
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
                setImageResource(R.mipmap.ic_launcher)
            }
            container.addView(imageView)
            return imageView
        }

        override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
            container.removeViewAt(position)
        }
    }
}
