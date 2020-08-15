package com.omkar.cavistademo.data.remote

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build

/**
 * Class will check for active internet connection before the api call
 */
class Network {
    companion object Utils {

        fun isConnected(context: Context): Boolean {
            val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            /**
             * This method will provide network status based on the build version of the sdk
             */
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                val capabilities =
                    cm.getNetworkCapabilities(cm.activeNetwork)
                if (capabilities != null) {
                    return (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) ||
                            capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) || capabilities.hasTransport(
                        NetworkCapabilities.TRANSPORT_ETHERNET
                    ))
                }
            } else {
                val activeNetwork = cm.activeNetworkInfo
                if (activeNetwork != null) {
                    // connected to the internet
                    return activeNetwork.type == ConnectivityManager.TYPE_WIFI || activeNetwork.type == ConnectivityManager.TYPE_MOBILE
                            || activeNetwork.type == ConnectivityManager.TYPE_ETHERNET
                }
            }
            return false
        }
    }
}