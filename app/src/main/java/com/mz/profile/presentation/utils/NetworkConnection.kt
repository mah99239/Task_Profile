package com.mz.profile.presentation.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.ConnectivityManager.NetworkCallback
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkRequest
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NetworkConnection @Inject constructor(context: Context) :
    MutableLiveData<Boolean>() {

    private val connectivityManager: ConnectivityManager by lazy(LazyThreadSafetyMode.NONE) {
        context.getSystemService(ConnectivityManager::class.java) as ConnectivityManager
    }


    private var _isAvailable = MutableLiveData<Boolean>()
    val isAvailable: LiveData<Boolean> get() = _isAvailable

    private val networkCallback: NetworkCallback = object : NetworkCallback() {
        override fun onAvailable(network: Network) {

            super.onAvailable(network)
            Timber.tag(TAG).e("onAvailable = $network")
            _isAvailable.postValue(true)
        }

        override fun onLost(network: Network) {
            super.onLost(network)
            _isAvailable.postValue( false)
            Timber.tag(TAG).e("onLost = $network")

        }

        override fun onCapabilitiesChanged(
            network: Network,
            networkCapabilities: NetworkCapabilities
        ) {
            super.onCapabilitiesChanged(network, networkCapabilities)
            val unmetered =
                networkCapabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_NOT_METERED)

            _isAvailable.postValue( unmetered)

        }
    }


    public override fun onActive() {
        super.onActive()
        registerNetworkCallback()
        _isAvailable.postValue(isActive())
        Timber.tag(TAG).e("IsActive: ${isActive()}")


    }

   public override fun onInactive() {
        super.onInactive()
        unregisterNetworkCallback()
    }


    private fun registerNetworkCallback() {
        val request = NetworkRequest.Builder()
            .addCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
            .addCapability(NetworkCapabilities.NET_CAPABILITY_FOTA)
            .addCapability(NetworkCapabilities.NET_CAPABILITY_MMS)
            .build()
        connectivityManager.registerNetworkCallback(request, networkCallback)
    }

    private fun unregisterNetworkCallback() {
        try {
            connectivityManager.unregisterNetworkCallback(networkCallback)
        } catch (e: Exception) {
            Timber.tag(TAG).e(e)
        }

    }

    private fun isActive(): Boolean {
        val capabilities = connectivityManager.getNetworkCapabilities(
            connectivityManager.activeNetwork
        )
        return capabilities != null &&
                (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) ||
                        capabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET) ||
                        capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) ||
                        capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET))
    }




    companion object {
        private const val TAG = "NetworkConnection"
    }
}