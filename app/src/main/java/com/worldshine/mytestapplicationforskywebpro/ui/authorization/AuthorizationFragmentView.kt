package com.worldshine.mytestapplicationforskywebpro.ui.authorization

import moxy.MvpView
import moxy.viewstate.strategy.OneExecutionStateStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(OneExecutionStateStrategy::class)
interface AuthorizationFragmentView : MvpView {
}