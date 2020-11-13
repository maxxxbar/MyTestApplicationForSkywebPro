package com.worldshine.mytestapplicationforskywebpro.ui.pictures

import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndSingleStrategy::class)
interface PicturesFragmentView: MvpView {

}