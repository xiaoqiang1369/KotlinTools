package xiaoqiang.com.kotlintools.extensions

import android.content.Context
import android.widget.ImageView
import androidx.annotation.DrawableRes
import com.bumptech.glide.Glide

fun ImageView.loadUrlImage(url: String, loadOptions: LoadOptions? = null){
    loadImage(context, this, url, loadOptions)
}


private const val ERROR_RES_ID = -1

data class LoadOptions(@DrawableRes var placeHolderResId: Int = ERROR_RES_ID,
                       @DrawableRes var errorResId: Int = ERROR_RES_ID,
                       val imageSize: ImageSize? = null)

data class ImageSize(var width: Int, var height: Int)


private fun loadImage(context: Context, imageView: ImageView, url: String, options: LoadOptions? = null) {
    loadImageWithGlide(context, imageView, url, options)
}


/**
 * load image by Glide
 */
private fun loadImageWithGlide(context: Context, imageView: ImageView, url: String, options: LoadOptions?) {
    var builder = Glide.with(context).load(url)

    if (options != null) {
        if (options.placeHolderResId != ERROR_RES_ID) {
            builder = builder.placeholder(options.placeHolderResId)
        }
        if (options.errorResId != ERROR_RES_ID) {
            builder = builder.error(options.errorResId)
        }
        if (options.imageSize != null) {
            builder = builder.override(options.imageSize.width, options.imageSize.height)
        }
    }

    builder.into(imageView)
}
