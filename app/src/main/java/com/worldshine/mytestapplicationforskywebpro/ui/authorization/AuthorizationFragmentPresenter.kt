package com.worldshine.mytestapplicationforskywebpro.ui.authorization

import android.annotation.SuppressLint
import com.worldshine.mytestapplicationforskywebpro.di.component.DaggerNetworkComponent
import com.worldshine.mytestapplicationforskywebpro.di.module.NetworkModule
import com.worldshine.mytestapplicationforskywebpro.network.Rest
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import moxy.InjectViewState
import moxy.MvpPresenter
import okhttp3.Interceptor
import javax.inject.Inject

@InjectViewState
class AuthorizationFragmentPresenter : MvpPresenter<AuthorizationFragmentView>() {

    companion object {
        private const val WEATHER_BASE_URL = "https://api.openweathermap.org/"
        private const val APP_ID_QUERY = "appid"
        private const val APP_ID_VALUE = "c35880b49ff95391b3a6d0edd0c722eb"
    }

    private val compositeDisposable = CompositeDisposable()
    private val interceptor = getInterceptorForWeatherAppId(APP_ID_QUERY, APP_ID_VALUE)

    @Inject
    lateinit var rest: Rest

    init {
        injectDependency(interceptor)
    }

    private fun getInterceptorForWeatherAppId(key: String, value: String): Interceptor {
        return Interceptor.invoke { chain ->
            val original = chain.request()
            val originalHttpUrl = original.url
            val url = originalHttpUrl.newBuilder()
                .addQueryParameter(key, value)
                .build()
            val requestBuilder = original.newBuilder()
                .url(url)
            chain.proceed(requestBuilder.build())
        }
    }

    private fun injectDependency(interceptor: Interceptor?) {
        val networkComponent =
            DaggerNetworkComponent.builder()
                .networkModule(
                    NetworkModule(
                        mBaseUrl = WEATHER_BASE_URL,
                        mInterceptor = interceptor
                    )
                ).build()

        networkComponent.inject(this)
    }

    @SuppressLint("CheckResult")
    fun getWeather() {
        rest.getWeather()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    //onNext
                    viewState.showSnackbars(
                        it.name,
                        it.main.temp.toInt().toString(),
                        it.weather[0].description,
                        it.main.humidity.toString()
                    )
                },
                { throwable ->
                    //OnError
                    throwable.localizedMessage?.let {
                        viewState.showError(it)
                    }

                },
                {
                    //onComplete
                    viewState.showProgressbar(false)
                },
                {
                    //onSubscribe
                    compositeDisposable.add(it)
                    viewState.showProgressbar(true)
                }
            )
    }

    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable.dispose()
    }
}