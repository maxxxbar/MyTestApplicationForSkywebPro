package com.worldshine.mytestapplicationforskywebpro.di.component

import com.worldshine.mytestapplicationforskywebpro.BaseApp
import com.worldshine.mytestapplicationforskywebpro.di.module.ApplicationModule
import dagger.Component

@Component(modules = [ApplicationModule::class])
interface ApplicationComponent {
    fun inject(application: BaseApp)
}