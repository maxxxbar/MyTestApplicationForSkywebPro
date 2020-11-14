package com.worldshine.mytestapplicationforskywebpro.di.module

import android.app.Application
import com.worldshine.mytestapplicationforskywebpro.BaseApp
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module
class ApplicationModule(private val baseApp: BaseApp) {

    @Provides
    @Singleton
    fun provideApplication(): Application = baseApp
}