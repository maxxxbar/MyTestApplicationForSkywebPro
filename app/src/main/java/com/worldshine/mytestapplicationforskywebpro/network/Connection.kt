package com.worldshine.mytestapplicationforskywebpro.network

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory


object Connection {

    private const val BASE_URL = "https://picsum.photos"

    private val httpLoggingInterceptor =
        HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
    private val clientForPictures =
        OkHttpClient().newBuilder().addInterceptor(httpLoggingInterceptor).build()

    fun getPicturesWithRetrofit(): Rest = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .addConverterFactory(GsonConverterFactory.create())
        .client(clientForPictures)
        .build()
        .create(Rest::class.java)

}