package xiaoqiang.com.kotlintools.image

/**
 *   description: used to load images from network
 *   created by crx on 2019/3/5 15:54
 */

//const val ERROR_RES_ID = -1
//
//data class LoadOptions(@DrawableRes var placeHolderResId: Int = ERROR_RES_ID,
//                       @DrawableRes var errorResId: Int = ERROR_RES_ID,
//                       val imageSize: ImageSize? = null)
//
//data class ImageSize(var width: Int, var height: Int)
//
//
//fun loadImage(context: Context, imageView: ImageView, url: String, options: LoadOptions? = null) {
//    loadImageWithGlide(context, imageView, url, options)
//}
//
//
///**
// * load image by Glide
// */
//private fun loadImageWithGlide(context: Context, imageView: ImageView, url: String, options: LoadOptions?) {
//    var builder = Glide.with(context).load(url)
//
//    if (options != null) {
//        if (options.placeHolderResId != ERROR_RES_ID) {
//            builder = builder.placeholder(options.placeHolderResId)
//        }
//        if (options.errorResId != ERROR_RES_ID) {
//            builder = builder.error(options.errorResId)
//        }
//        if (options.imageSize != null) {
//            builder = builder.override(options.imageSize.width, options.imageSize.height)
//        }
//    }
//
//    builder.into(imageView)
//}
