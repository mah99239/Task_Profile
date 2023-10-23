package com.mz.profile.di

import com.mz.profile.domain.utils.AppDispatchers
import com.mz.profile.domain.utils.Dispatcher
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import javax.inject.Singleton


@InstallIn(SingletonComponent::class)
@Module
internal object CoroutinesScopesModule
{
   @Singleton
   @Provides
   fun providesCoroutineScope(
      @Dispatcher(AppDispatchers.DEFAULT)
      defaultDispatcher: CoroutineDispatcher,
                             )
        : CoroutineScope = CoroutineScope(SupervisorJob() + defaultDispatcher)
   
   
}