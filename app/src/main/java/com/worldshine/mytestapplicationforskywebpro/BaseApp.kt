package com.worldshine.mytestapplicationforskywebpro

import android.app.Application
import com.worldshine.mytestapplicationforskywebpro.di.component.ApplicationComponent
import com.worldshine.mytestapplicationforskywebpro.di.component.DaggerApplicationComponent

class BaseApp : Application() {

    lateinit var component: ApplicationComponent

    override fun onCreate() {
        super.onCreate()
        component = DaggerApplicationComponent.create()
        component.inject(this)
    }
}