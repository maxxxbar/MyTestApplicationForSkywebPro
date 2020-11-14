package com.worldshine.mytestapplicationforskywebpro.ui.authorization

import android.util.Log
import com.worldshine.mytestapplicationforskywebpro.network.Connection
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import moxy.MvpPresenter

class AuthorizationFragmentPresenter : MvpPresenter<AuthorizationFragmentView>() {

    private  val TAG = javaClass.simpleName

    fun getWeather() {
        val rest =
            Connection.getWeatherWithRetrofit("https://api.openweathermap.org/")
        rest.getWeather().subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(
                onSuccess = {
                    Log.d(TAG, "Success: $it")
                },
                onError = {
                    Log.d(TAG, "Error: ${it.localizedMessage}")
                }
            )
    }
}