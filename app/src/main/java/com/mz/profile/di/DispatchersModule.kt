package com.mz.profile.di

import com.mz.profile.domain.utils.AppDispatchers
import com.mz.profile.domain.utils.Dispatcher
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

@Module
@InstallIn(SingletonComponent::class)
internal object DispatchersModule
{

   
   @Dispatcher(AppDispatchers.DEFAULT)
   @Provides
   fun providesDefaultDispatcher(): CoroutineDispatcher = Dispatchers.Default
   
   @Dispatcher(AppDispatchers.IO)
   @Provides
   fun providesIoDispatcher(): CoroutineDispatcher = Dispatchers.IO
   
   @Dispatcher(AppDispatchers.MAIN)
   @Provides
   fun providesMainDispatcher(): CoroutineDispatcher = Dispatchers.Main
   
   
}
