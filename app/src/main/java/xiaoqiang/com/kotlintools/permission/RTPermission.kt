package xiaoqiang.com.kotlintools.permission

import android.Manifest
import android.app.Activity
import android.content.pm.PackageManager
import android.support.v4.app.ActivityCompat

/**
 *   description: run time permission request
 *   created by crx on 2018/9/21 14:25
 *
 *   --use example--
 *
 *   #request a permission:
 *   if(RTPermission.shouldRequest(activity, RTPermission.Pms.WRITE_STORAGE)){
         RTPermission.request(activity, RTPermission.Pms.WRITE_STORAGE)
     }else{
        //already has the permission, do whatever you like
     }
 *
 *   #get request result:
 *   override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (RTPermission.granted(requestCode, RTPermission.Pms.WRITE_STORAGE.code, grantResults)){
            //permission granted, do whatever you like
        }else{
            //user denied the permission
            if(RTPermission.checkedNotAskAgain(this, permissions[0])){
                //user denied the permission and checked "don't ask again"
            }
        }
    }
 */
object RTPermission {

    enum class Pms(val pmsName: String, val code: Int) {
        READ_STORAGE(Manifest.permission.READ_EXTERNAL_STORAGE, 1110),
        WRITE_STORAGE(Manifest.permission.WRITE_EXTERNAL_STORAGE, 1120),
        INTERNET(Manifest.permission.INTERNET, 1190),
        CAMERA(Manifest.permission.CAMERA, 1130),
        FINE_LOCATION(Manifest.permission.ACCESS_FINE_LOCATION, 1140),
        COARSE_LOCATION(Manifest.permission.ACCESS_COARSE_LOCATION, 1150),
        CALL_PHONE(Manifest.permission.CALL_PHONE, 1160),
        RECORD_AUDIO(Manifest.permission.RECORD_AUDIO, 1170),
        READ_CONTACTS(Manifest.permission.READ_CONTACTS, 1180)
    }

    /**
     * check whether you should request a particular permission
     * return true if has got the permission
     */
    fun shouldRequest(activity: Activity, permission: Pms): Boolean {
        return ActivityCompat.checkSelfPermission(activity, permission.pmsName) == PackageManager.PERMISSION_DENIED
    }

    /**
     * request a particular permission
     */
    fun request(activity: Activity, pms: Pms) {
        requestPermission(activity, pms)
    }

    private fun requestPermission(activity: Activity, pms: RTPermission.Pms) {
        ActivityCompat.requestPermissions(activity, arrayOf(pms.pmsName), pms.code)
    }

    /**
     * Check whether the user has been granted a particular permission in method #onRequestPermissionsResult#
     * @param resultRequestCode the request code returned in method #onRequestPermissionsResult#
     * @param requestCode the request code you passed to match with a result
     * return true if the user granted the permission you requested;
     * return false if the user denied the permission you requested or the user selected "Don't ask again"
     */
    fun granted(resultRequestCode: Int, requestCode: Int, grantResults: IntArray): Boolean {
        return requestCode == resultRequestCode
                && grantResults.isNotEmpty()
                && grantResults[0] == PackageManager.PERMISSION_GRANTED
    }

    /**
     * Check if the user denied a permission and checked "Don't ask again"
     */
    fun checkedNotAskAgain(activity: Activity, permission: String): Boolean{
        return if(!ActivityCompat.shouldShowRequestPermissionRationale(activity, permission))
            true
        else
            return false
    }

}