package xiaoqiang.com.kotlintools.view

import android.content.Context
import android.graphics.*
import android.graphics.drawable.BitmapDrawable
import android.util.AttributeSet
import android.widget.ImageView
import xiaoqiang.com.kotlintools.R

/**
 *   description: 圆角图片
 *   created by crx on 2018/12/25 17:51
 */
class RoundCornerImageView @JvmOverloads constructor(
    context: Context,
    attributeSet: AttributeSet? = null,
    styleRes: Int = 0
) : ImageView(context, attributeSet, styleRes){

    private var mRadius: Float
    private val mRectF: RectF = RectF()
    private val mPaint: Paint = Paint(Paint.ANTI_ALIAS_FLAG)
    lateinit var mShader: BitmapShader
    private var isInitialized = false

    init {
        val styleValues = context.obtainStyledAttributes(attributeSet, R.styleable.RoundCornerImageView)
        mRadius = styleValues.getDimension(R.styleable.RoundCornerImageView_radius, 0f)
        styleValues.recycle()
    }

    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        super.onLayout(changed, left, top, right, bottom)

        initAfterLayout()
    }


    private fun initAfterLayout(){
        mRectF.apply {
            left = 0f
            right = width.toFloat()
            top = 0f
            bottom = height.toFloat()
        }

        initShader()

    }

    private fun initShader(){
        if (isInitialized || drawable == null) return
        val bitmapDrawable: BitmapDrawable = drawable as BitmapDrawable
        mShader = BitmapShader(resizeBitmap(bitmapDrawable.bitmap), Shader.TileMode.CLAMP, Shader.TileMode.CLAMP)
        mPaint.shader = mShader
        isInitialized = true
    }

    override fun onDraw(canvas: Canvas?) {
        if(isInitialized && mRadius > 0){
            canvas?.drawRoundRect(mRectF, mRadius, mRadius, mPaint)
        }else{
            super.onDraw(canvas)
        }
    }


    //缩放bitmap至控件大小
    private fun resizeBitmap(bitmap: Bitmap): Bitmap {
        if(bitmap.height == height && bitmap.width == width){
            return bitmap
        }

        if(ScaleType.FIT_XY == scaleType){
            return Bitmap.createScaledBitmap(bitmap, width, height, true)
        }

        //centerCrop 裁取图片
        val scaleFloat = Math.max(width.toFloat()/bitmap.width, height.toFloat()/bitmap.height)
        val scaleBitmap = Bitmap.createScaledBitmap(bitmap,
            (bitmap.width * scaleFloat).toInt(),
            (bitmap.height*scaleFloat).toInt(), true)

        return Bitmap.createBitmap(scaleBitmap,
            (scaleBitmap.width - width) / 2,
            (scaleBitmap.height - height) / 2,
            width,
            height)
    }

}