package com.worldshine.mytestapplicationforskywebpro.network

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory


object Connection {

    private const val BASE_URL = "https://picsum.photos"
    private const val APP_ID_QUERY = "appid"
    private const val APP_ID_VALUE = "c35880b49ff95391b3a6d0edd0c722eb"


    private val httpLoggingInterceptor =
        HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
    private val clientForPictures =
        OkHttpClient().newBuilder().addInterceptor(httpLoggingInterceptor).build()
    private val clientForWeather = OkHttpClient().newBuilder().addInterceptor(object : Interceptor {
        override fun intercept(chain: Interceptor.Chain): Response {
            val original = chain.request()
            val originalHttpUrl = original.url
            val url = originalHttpUrl.newBuilder()
                .addQueryParameter(APP_ID_QUERY, APP_ID_VALUE)
                .build()
            val requestBuilder = original.newBuilder()
                .url(url)
            return chain.proceed(requestBuilder.build())
        }
    }).addInterceptor(httpLoggingInterceptor).build()

    fun getPicturesWithRetrofit(): Rest = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .addConverterFactory(GsonConverterFactory.create())
        .client(clientForPictures)
        .build()
        .create(Rest::class.java)

    fun getWeatherWithRetrofit(baseUrl: String): Rest = Retrofit.Builder()
        .baseUrl(baseUrl)
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .addConverterFactory(GsonConverterFactory.create())
        .client(clientForWeather)
        .build()
        .create(Rest::class.java)
}