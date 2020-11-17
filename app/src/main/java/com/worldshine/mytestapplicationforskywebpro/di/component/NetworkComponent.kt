package com.worldshine.mytestapplicationforskywebpro.di.component

import androidx.fragment.app.Fragment
import com.worldshine.mytestapplicationforskywebpro.di.module.NetworkModule
import com.worldshine.mytestapplicationforskywebpro.ui.authorization.AuthorizationFragment
import com.worldshine.mytestapplicationforskywebpro.ui.authorization.AuthorizationFragmentPresenter
import com.worldshine.mytestapplicationforskywebpro.ui.main.MainActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [NetworkModule::class])
interface NetworkComponent {
    fun inject(activity: MainActivity)
    fun inject(authorizationFragmentPresenter: AuthorizationFragmentPresenter)
}