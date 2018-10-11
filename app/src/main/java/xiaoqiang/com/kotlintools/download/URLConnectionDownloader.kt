package xiaoqiang.com.kotlintools.download

import java.io.BufferedInputStream
import java.io.File
import java.io.FileOutputStream
import java.net.HttpURLConnection
import java.net.URL

/**
 *   description: use HttpURLConnection to download a file by its url
 *   created by crx on 2018/10/10 16:10
 */

public class URLConnectionDownloader(private val url: String){

    private var urlConnection: HttpURLConnection
    private var fileLength = 0
    private var filePath = ""
    private var fileName = ""
    private var downloadListener: FileDownloadListener? = null
    private var downloadFlag = true

    init {
        val mURL = URL(url)
        urlConnection = mURL.openConnection() as HttpURLConnection
        urlConnection.connectTimeout = 15 * 1000
        urlConnection.readTimeout = 15 * 1000
        urlConnection.connect()
        //
        fileLength = urlConnection.contentLength
    }

    interface FileDownloadListener{
        fun onDownloadStart()
        fun onDownloading(percent: Int)
        fun onDownloadCanceled()
        fun onDownloadComplete(path: String)
    }

    /**
     * set download file path and file name
     */
    fun setDestinationDir(path: String, name: String): URLConnectionDownloader{
        filePath = path
        fileName = name
        return this
    }

    fun setDownloadListener(listener: FileDownloadListener): URLConnectionDownloader{
        downloadListener = listener
        return this
    }

    /**
     * cancel the downloading task
     */
    fun cancel(){
        downloadFlag = false
    }

    fun start(){
        if(url.isEmpty())
            throw IllegalArgumentException("Url should not be empty!")
        if(filePath.isEmpty() || fileName.isEmpty())
            throw IllegalArgumentException("You must set file download path and file name before start!")

        downloadListener?.onDownloadStart()

        //create file if it dose not exist
        val file = File(filePath)
        if(!file.exists()) file.mkdirs()

        // input stream to read file - with 8k buffer
        val input = BufferedInputStream(urlConnection.inputStream, 8 * 1024)
        //output stream to write file
        val output = FileOutputStream(filePath + File.separator + fileName)
        val data = ByteArray(1024)
        var totalReadSize = 0L
        var count: Int

        //write file to local
        while (downloadFlag){
            count = input.read(data)
            if(count == -1) break
            output.write(data, 0, count)
            //calculate download percent
            totalReadSize += count
            downloadListener?.onDownloading((totalReadSize * 1f / fileLength * 100).toInt())
        }

        //close after download complete
        output.flush()
        output.close()
        input.close()

        if(!downloadFlag && totalReadSize < fileLength){
            downloadListener?.onDownloadCanceled()
        }else{
            downloadListener?.onDownloadComplete(filePath)
        }


    }
}