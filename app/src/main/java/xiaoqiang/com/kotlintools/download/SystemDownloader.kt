package xiaoqiang.com.kotlintools.download

import android.app.DownloadManager
import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.LifecycleObserver
import android.arch.lifecycle.OnLifecycleEvent
import android.content.*
import android.net.Uri
import android.os.Environment
import android.support.v4.app.FragmentActivity
import java.io.File
import java.net.URI

/**
 *   description: Use system DownloadManager to download files by uri
 *   created by crx on 2018/10/9 15:43
 */
class SystemDownloader(private val context: Context, url: String) : LifecycleObserver{

    private var downloadManager: DownloadManager = context.getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager
    private var request: DownloadManager.Request = DownloadManager.Request(Uri.parse(url))
    //
    private var setDestinationDir: Boolean = false
    private var downloadListener: DownloadListener? = null
    private var fileUri: Uri? = null
    private var repeatDownload = true
    private var uriMap = mutableMapOf<Long, Uri?>()

    init {
        request.allowScanningByMediaScanner()
        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
    }


    abstract class DownloadListener{
        /**
         * called when a download is complete
         */
        abstract fun onDownloadComplete(downloadId: Long)

        /**
         * called if the file you want to download already exists
         * ,optional to override
         */
        open fun onFileExists(){}

        /**
         * called if the file you want to download is downloading
         * ,optional to override
         */
        open fun onFileDownloading(){}
    }

    /**
     * listen to download . If the context is not a FragmentActivity/AppCompatActivity,
     * please call #SystemDownloader.onDestroy()# in Activity/Fragment's onDestroy method
     * to unregister download complete Receiver.
     */
    fun setDownloadListener(listener: DownloadListener) : SystemDownloader{
        this.downloadListener = listener
        context.registerReceiver(onDownloadComplete, IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE))
        //if the context is FragmentActivity, then listen to it's lifecycle
        (context as? FragmentActivity)?.lifecycle?.addObserver(this)
        return this
    }


    fun setTitle(title: String): SystemDownloader {
        request.setTitle(title)
        return this
    }

    fun setDescription(description: String): SystemDownloader {
        request.setDescription(description)
        return this
    }

    /**
     * whether to display this download in the Downloads UI
     */
    fun setVisibleInDownloadsUi(visible: Boolean): SystemDownloader {
        request.setVisibleInDownloadsUi(visible)
        return this
    }

    /**
     * Set the local destination for the downloaded file to a path within
     * the public external storage directory (as returned by
     * Environment.getExternalStoragePublicDirectory(String)).
     */
    fun setDestinationInExternalPublicDir(dirType: String, fileName: String): SystemDownloader {
        val file = Environment.getExternalStoragePublicDirectory(dirType)
        if (!file.exists()) file.mkdirs()
        fileUri = Uri.withAppendedPath(Uri.fromFile(file), fileName)
        request.setDestinationInExternalPublicDir(dirType, fileName)
        setDestinationDir = true
        return this
    }

    /**
     * Set the local destination for the downloaded file to a path within
     * the application's external files directory (as returned by
     * Context.getExternalFilesDir(String)).
     */
    fun setDestinationInExternalFileDir(dirType: String, fileName: String): SystemDownloader {
        val file = context.getExternalFilesDir(dirType)
        if(!file.exists()) file.mkdirs()
        fileUri = Uri.withAppendedPath(Uri.fromFile(file), fileName)
        request.setDestinationInExternalFilesDir(context, dirType, fileName)
        setDestinationDir = true
        return this
    }

    /**
     * Set weather a file can be downloaded repeatedly，default is true
     * if set to false，you can't download an existed file
     */
    fun setRepeatableDownload(repeat: Boolean): SystemDownloader{
        repeatDownload = repeat
        return this
    }

    /**
     * check weather the file exists
     */
    fun exists(): Boolean{
        val path = URI(fileUri.toString()).path
        return File(path).exists()
    }

    fun start(): Long{
        if(!repeatDownload){
            if(uriMap.containsValue(fileUri)) {
                downloadListener?.onFileDownloading()
                return -1
            }
            if(exists()) {
                downloadListener?.onFileExists()
                return -1
            }
        }

        if(!setDestinationDir){
            throw Exception("Destination file directory must be set!")
        }else{
            val id = downloadManager.enqueue(request)
            uriMap[id] = fileUri
            return id
        }
    }

    private val onDownloadComplete = object : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {
            val id = intent.getLongExtra(DownloadManager.EXTRA_DOWNLOAD_ID, -1)
            uriMap.remove(id)
            if(exists()) downloadListener?.onDownloadComplete(id)
        }
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    fun onDestroy(){
        context.unregisterReceiver(onDownloadComplete)
    }

}