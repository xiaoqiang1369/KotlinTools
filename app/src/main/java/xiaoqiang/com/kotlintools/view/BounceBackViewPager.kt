package xiaoqiang.com.kotlintools.view

import android.animation.Animator
import android.animation.ValueAnimator
import android.content.Context
import android.support.v4.view.ViewPager
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View

/**
 * 有边界滑动回弹效果的ViewPager
 * created by chenrenxiang on 2018/7/19 15:40
 */
class BounceBackViewPager @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null)
    : ViewPager(context, attrs) {

    private var handleMove = false     //是否拦截并处理了滑动
    private var preX = 0f
    private var isAnimationPlaying = false
    private var touchDownPageIndex = 0
    private var touchDownTranslationX = 0f
    private val ratio = 0.25f//摩擦系数
    private val minScrollDistance = 4

    init {
        overScrollMode = View.OVER_SCROLL_NEVER
    }

    override fun onInterceptTouchEvent(ev: MotionEvent): Boolean {
        when (ev.action) {
            MotionEvent.ACTION_DOWN -> {
                preX = ev.x
                touchDownPageIndex = currentItem
                touchDownTranslationX = translationX
            }
            MotionEvent.ACTION_MOVE -> if (isOverScroll(ev)) {
                return true
            }
        }
        return super.onInterceptTouchEvent(ev)
    }

    override fun onTouchEvent(ev: MotionEvent): Boolean {
        if (isAnimationPlaying || adapter == null) {
            return super.onTouchEvent(ev)
        }
        when (ev.action) {
            MotionEvent.ACTION_UP -> {
                recoveryPosition()
                handleMove = false
            }
            MotionEvent.ACTION_MOVE -> if (isOverScroll(ev)) {
                handleOverScrollMove(ev)
            }
        }

        return handleMove || super.onTouchEvent(ev)
    }

    private fun isOverScroll(event: MotionEvent): Boolean {
        return (touchDownPageIndex == 0 && event.x > preX)
                || (touchDownPageIndex == adapter?.count?.minus(1) && event.x < preX)
    }

    private fun handleOverScrollMove(ev: MotionEvent) {
        val nowX = ev.x
        val offset = nowX - preX
        if (Math.abs(offset) > minScrollDistance) {
            handleScroll(offset)
            preX = nowX
        }
    }

    private fun handleScroll(offset: Float) {
        handleMove = true
        translationX = translationX.plus(offset * ratio)
    }

    private fun recoveryPosition() {
        if (translationX == touchDownTranslationX || !handleMove) return

        val animator = ValueAnimator.ofFloat(translationX, touchDownTranslationX).setDuration(240).apply {
            addUpdateListener { animation ->
                translationX = animation.animatedValue as Float
            }

            addListener(object : Animator.AnimatorListener {
                override fun onAnimationStart(animation: Animator) {}
                override fun onAnimationCancel(animation: Animator) {}
                override fun onAnimationRepeat(animation: Animator) {}
                override fun onAnimationEnd(animation: Animator) {
                    isAnimationPlaying = false
                }
            })
        }
        animator.start()
        isAnimationPlaying = true
    }


}