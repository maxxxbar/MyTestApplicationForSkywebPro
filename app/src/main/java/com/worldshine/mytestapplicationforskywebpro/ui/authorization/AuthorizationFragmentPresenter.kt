package com.worldshine.mytestapplicationforskywebpro.ui.authorization

import android.annotation.SuppressLint
import com.worldshine.mytestapplicationforskywebpro.network.Connection
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import moxy.MvpPresenter

class AuthorizationFragmentPresenter : MvpPresenter<AuthorizationFragmentView>() {

    private val compositeDisposable = CompositeDisposable()

    @SuppressLint("CheckResult")
    fun getWeather() {
        val rest =
            Connection.getWeatherWithRetrofit("https://api.openweathermap.org/")
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