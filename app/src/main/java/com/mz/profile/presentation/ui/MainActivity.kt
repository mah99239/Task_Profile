package com.mz.profile.presentation.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.LiveData
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.mz.profile.R
import com.mz.profile.databinding.ActivityMainBinding
import com.mz.profile.presentation.utils.NetworkConnection
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private var _binding: ActivityMainBinding? = null
    private val binding: ActivityMainBinding get() = _binding!!

    @Inject
    lateinit var networkConnection: NetworkConnection
    private lateinit var navController: NavController


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(_binding?.root)
        binding.lifecycleOwner = this
        handleTitle()
        observerViewModel()


    }

    private fun handleTitle() {
        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment) as NavHostFragment

        navController = navHostFragment.navController
        navController.addOnDestinationChangedListener { _, destination, _ ->
            // Update the activity's title based on the fragment that the user is navigating to
            title = destination.label
        }
    }


    private fun observerViewModel()
    {
        
            
            lifecycleScope.launch {
                networkConnection.isAvailable.collect {
                    binding.isAvailable = it
                    Timber.d("IsAvailable = $it")
                }
            }
        
    }

    override fun onStart() {
        super.onStart()
        

    }

    override fun onDestroy() {
        super.onDestroy()
        networkConnection.onDestroy()
        _binding = null

    }


    private fun removeObservers() {
        for (field in networkConnection.javaClass.declaredFields) {
            field.isAccessible = true
            val fieldValue = field.get(networkConnection)
            if (fieldValue is LiveData<*>) {
                fieldValue.removeObservers(this)
            }
        }
    }
}