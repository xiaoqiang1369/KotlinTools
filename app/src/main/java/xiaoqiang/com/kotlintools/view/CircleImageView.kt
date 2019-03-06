package xiaoqiang.com.kotlintools.view

import android.content.Context
import android.graphics.*
import android.graphics.drawable.BitmapDrawable
import android.util.AttributeSet
import android.widget.ImageView
import android.graphics.Bitmap


/**
 *   description: 圆形ImageView
 *   created by crx on 2018/12/24 10:00
 */

class CircleImageView @JvmOverloads constructor(
    context: Context,
    attributeSet: AttributeSet? = null,
    styleRes: Int = 0
) : ImageView(context, attributeSet, styleRes) {


    private var mWidth = 0
    private var isInitialized = false
    private var mShader: BitmapShader? = null
    private val mPaint: Paint = Paint(Paint.ANTI_ALIAS_FLAG)

    private fun init() {
        if (isInitialized || drawable == null) return

        val bitmapDrawable: BitmapDrawable = drawable as BitmapDrawable
        val bitmap = resizeBitmap(bitmapDrawable.bitmap)
        mShader = BitmapShader(bitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP)

        isInitialized = true
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        //长和宽一致
        val minLength = Math.min(measuredWidth, measuredHeight)
        setMeasuredDimension(minLength, minLength)
    }

    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        super.onLayout(changed, left, top, right, bottom)
        mWidth = Math.min(height, width)
        init()
    }

    override fun onDraw(canvas: Canvas?) {
        if (mPaint.shader == null) { mPaint.shader = mShader }

        if (isInitialized) {
            canvas?.drawCircle(mWidth / 2f, mWidth / 2f, mWidth / 2f, mPaint)
        } else {
            super.onDraw(canvas)
        }
    }

    //缩放bitmap至控件大小
    private fun resizeBitmap(bitmap: Bitmap): Bitmap {
        if (bitmap.width == bitmap.height) {
            return Bitmap.createScaledBitmap(bitmap, mWidth, mWidth, false)
        }
        //如果长宽不相等，裁取图片
        val width = Math.min(bitmap.width, bitmap.height)
        val cutBitmap = Bitmap.createBitmap(bitmap,
            (bitmap.width - width) / 2,
            (bitmap.height - width) / 2,
            width,
            width)
        return Bitmap.createScaledBitmap(cutBitmap, mWidth, mWidth, false)
    }

}