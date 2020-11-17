package com.worldshine.mytestapplicationforskywebpro.di.component

import com.worldshine.mytestapplicationforskywebpro.di.module.ActivityModule
import com.worldshine.mytestapplicationforskywebpro.di.module.NetworkModule
import com.worldshine.mytestapplicationforskywebpro.ui.main.MainActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ActivityModule::class, NetworkModule::class])
interface ActivityComponent {
    fun inject(mainActivity: MainActivity)
}