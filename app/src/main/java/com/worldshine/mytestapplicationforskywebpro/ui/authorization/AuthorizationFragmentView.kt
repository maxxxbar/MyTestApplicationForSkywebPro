package com.worldshine.mytestapplicationforskywebpro.ui.authorization

import moxy.MvpView
import moxy.viewstate.strategy.OneExecutionStateStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(OneExecutionStateStrategy::class)
interface AuthorizationFragmentView : MvpView {
    fun showSnackbars(
        city: String,
        weather: String,
        clouds: String,
        humidity: String
    )

    fun showError(error: String)
    fun showProgressbar(show: Boolean)
    fun btnOnClick()
    fun clearErrorForEmailAndPasswordTextField()
    fun showErrorInTextInputLayoutEmail(error: Int)
    fun showErrorInTextInputLayoutPassword(error: Int)


}