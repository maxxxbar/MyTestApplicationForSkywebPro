package com.worldshine.mytestapplicationforskywebpro.ui.pictures

import moxy.MvpView
import moxy.viewstate.strategy.OneExecutionStateStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(OneExecutionStateStrategy ::class)
interface PicturesFragmentView : MvpView {
    fun initAdapter()
}