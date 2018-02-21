package com.example.com.mptvshow.di

import android.content.Context
import com.example.com.mptvshow.BuildConfig
import com.example.com.mptvshow.R
import com.facebook.stetho.okhttp3.StethoInterceptor
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
class NetModule{

    @Provides
    @Singleton
    fun providesGson(): Gson {
        val gsonBuilder = GsonBuilder()
        return gsonBuilder.create()
    }

    @Provides
    @Singleton
    fun providesOkkHttpClient(): OkHttpClient {
        val builder = OkHttpClient.Builder()

        if(BuildConfig.DEBUG)
            builder.addNetworkInterceptor(StethoInterceptor())

        builder.connectTimeout(30, TimeUnit.SECONDS)
        builder.readTimeout(30, TimeUnit.SECONDS)
        builder.writeTimeout(30, TimeUnit.SECONDS)

        return builder.build()
    }

    @Provides
    @Singleton
    fun providesServerRetrofit(context : Context, gson : Gson, okHttpClient : OkHttpClient) : Retrofit {
        return Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(context.getString(R.string.base_url))
                .client(okHttpClient)
                .build()
    }

    @Provides
    @Singleton
    fun provideServerApi(retrofit: Retrofit): ServerApi{
        return retrofit.create(ServerApi::class.java)
    }
}