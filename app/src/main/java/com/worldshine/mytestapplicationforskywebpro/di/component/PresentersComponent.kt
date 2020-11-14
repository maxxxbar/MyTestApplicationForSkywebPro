package com.worldshine.mytestapplicationforskywebpro.di.component

import com.worldshine.mytestapplicationforskywebpro.di.module.PresentersModule
import com.worldshine.mytestapplicationforskywebpro.ui.authorization.AuthorizationFragmentPresenter
import com.worldshine.mytestapplicationforskywebpro.ui.pictures.PicturesFragmentPresenter
import dagger.Component

@Component(modules = [PresentersModule::class])
interface PresentersComponent {
    fun getPicturesFragmentPresenter(): PicturesFragmentPresenter
    fun getAuthorizationFragmentPresenter(): AuthorizationFragmentPresenter
}