package com.mz.profile.domain.utils

import javax.inject.Qualifier

@Retention(AnnotationRetention.RUNTIME)
@Qualifier
annotation class Dispatcher(val appDispatchers: AppDispatchers)
enum class AppDispatchers {
   IO,
   DEFAULT,
   MAIN}