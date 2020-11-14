package com.worldshine.mytestapplicationforskywebpro.di.component

import com.worldshine.mytestapplicationforskywebpro.ui.main.MainActivity
import com.worldshine.mytestapplicationforskywebpro.di.module.ActivityModule
import dagger.Component

@Component(modules = [ActivityModule::class])
interface ActivityComponent {
    fun inject(mainActivity: MainActivity)
}