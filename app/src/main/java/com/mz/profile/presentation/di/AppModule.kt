package com.mz.profile.presentation.di

import android.content.Context
import com.mz.profile.domain.utils.AppDispatchers
import com.mz.profile.domain.utils.Dispatcher
import com.mz.profile.presentation.utils.NetworkConnection
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineScope
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object AppModule {

    @Provides
    @Singleton
    fun provideNetworkConnection(@ApplicationContext context: Context,
                                  coroutineScope: CoroutineScope): NetworkConnection {
        return NetworkConnection(context, coroutineScope )
    }
}