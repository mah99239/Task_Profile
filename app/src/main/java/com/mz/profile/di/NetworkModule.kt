package com.mz.profile.di

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.mz.profile.data.interceptor.HttpRequestInterceptor
import com.mz.profile.data.remote.ApiService
import com.mz.profile.data.remote.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object NetworkModule
{
   
   
   @Provides
   @Singleton
   fun provideGsonBuilder(): Gson
   {
      return GsonBuilder()
         //.registerTypeAdapter(Date::class.java, GsonDateFormatAdapter(BuildConfig.API_DATE_TIME_FORMAT))
         .create()
   }
   
   @Provides
   @Singleton
   fun provideOkHttp(): OkHttpClient
   {
      return OkHttpClient.Builder()
         .addInterceptor(HttpRequestInterceptor())
         .connectTimeout(20, TimeUnit.SECONDS)
         .readTimeout(20, TimeUnit.SECONDS)
         .build()
   }
   
   
   @Provides
   @Singleton
   fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit
   {
      
      
      return Retrofit.Builder()
         .baseUrl(BASE_URL)
         .client(okHttpClient)
         .addCallAdapterFactory(CoroutineCallAdapterFactory())
         .addConverterFactory(GsonConverterFactory.create())
         .build()
   }
   
   @Provides
   @Singleton
   fun provideApiService(retrofit: Retrofit): ApiService
   {
      return retrofit.create(ApiService::class.java)
   }
   
}
