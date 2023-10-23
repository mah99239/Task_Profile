package com.mz.profile.presentation.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.ConnectivityManager.NetworkCallback
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkRequest
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.currentCoroutineContext
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NetworkConnection @Inject constructor(
   private val context: Context,
   private val coroutineScope: CoroutineScope,
                                           )
{
   
   
   private val connectivityManager: ConnectivityManager by lazy(LazyThreadSafetyMode.SYNCHRONIZED) {
      context.getSystemService(ConnectivityManager::class.java) as ConnectivityManager
   }
   
   private val _isAvailable = MutableStateFlow(isActive())
   val isAvailable: StateFlow<Boolean> = _isAvailable
      .stateIn(scope = coroutineScope, started = SharingStarted.Lazily,
         isActive())
   
   
   private val networkCallback: NetworkCallback = object : NetworkCallback()
   {
      override fun onAvailable(network: Network)
      {
         
         super.onAvailable(network)
         Timber.tag(TAG).d("onAvailable:network = $network")
         updateNetworkStatus(true)
      }
      
      override fun onLost(network: Network)
      {
         super.onLost(network)
         updateNetworkStatus(false)
         Timber.tag(TAG).d("onLost = $network")
         
      }
      
      override fun onCapabilitiesChanged(
         network: Network,
         networkCapabilities: NetworkCapabilities,
                                        )
      {
         super.onCapabilitiesChanged(network, networkCapabilities)
         val unmetered =
            networkCapabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_NOT_METERED)
         Timber.tag(TAG).d("onCapabilitiesChanged = $unmetered")
         
         // updateNetworkStatus(unmetered)
         
      }
   }
   
   init
   {
      registerNetworkCallback()
      updateNetworkStatus(isActive())
   }
   
   fun onDestroy()
   {
      unregisterNetworkCallback()
   }
   
   private fun updateNetworkStatus(isAvailable: Boolean)
   {
      _isAvailable.value = isAvailable
      
   }
   
   
   private fun registerNetworkCallback()
   {
      
      val request = NetworkRequest.Builder()
         .addCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
         .addCapability(NetworkCapabilities.NET_CAPABILITY_VALIDATED)
         .build()
      
      coroutineScope.launch {
         try
         {
            Timber.d("Thread : ${currentCoroutineContext()}")
            connectivityManager.registerNetworkCallback(request, networkCallback)
            
         } catch (e: Exception)
         {
            Timber.tag(TAG).e(e)
         }
      }
   }
   
   private fun unregisterNetworkCallback()
   {
      coroutineScope.launch {
         try
         {
            connectivityManager.unregisterNetworkCallback(networkCallback)
         } catch (e: Exception)
         {
            Timber.tag(TAG).e(e)
         }
      }
      
   }
   
   private fun isActive(): Boolean
   {
      val capabilities = connectivityManager.getNetworkCapabilities(
         connectivityManager.activeNetwork
                                                                   )
      return capabilities != null &&
           (
                (capabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET) &&
                     capabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_VALIDATED) &&
                     (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) ||
                          capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)))
                )
   }
   
   
   companion object
   {
      private const val TAG = "NetworkConnection"
   }
}