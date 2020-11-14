package com.worldshine.mytestapplicationforskywebpro.di.module

import com.worldshine.mytestapplicationforskywebpro.ui.authorization.AuthorizationFragmentPresenter
import com.worldshine.mytestapplicationforskywebpro.ui.pictures.PicturesFragmentPresenter
import dagger.Module
import dagger.Provides

@Module
class PresentersModule {

    @Provides
    fun providePicturesPresenter(): PicturesFragmentPresenter = PicturesFragmentPresenter()

    @Provides
    fun authorizationPicturesPresenter(): AuthorizationFragmentPresenter =
        AuthorizationFragmentPresenter()
}