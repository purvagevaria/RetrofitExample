package com.notimp.util

import android.content.Context
import android.net.ConnectivityManager
import android.widget.Toast
import com.pg.purvapractical.R

object NetworkUtils {
    fun isOnline(context: Context): Boolean {
        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val netInfo = cm.activeNetworkInfo

        return (netInfo != null
                && netInfo.isConnectedOrConnecting
                && cm.activeNetworkInfo.isAvailable
                && cm.activeNetworkInfo.isConnected)

    }

    fun checkInternetAndShowToast(context: Context): Boolean {
        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val netInfo = cm.activeNetworkInfo

        var isAbailable = netInfo != null
                && netInfo.isConnectedOrConnecting
                && cm.activeNetworkInfo.isAvailable
                && cm.activeNetworkInfo.isConnected

        if (!isAbailable) {
            Toast.makeText(context, context.getString(R.string.no_internet), Toast.LENGTH_LONG)
                .show()

        }

        return isAbailable
    }


}
