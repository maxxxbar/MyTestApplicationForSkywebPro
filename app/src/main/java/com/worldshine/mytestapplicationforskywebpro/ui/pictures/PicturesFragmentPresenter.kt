package com.worldshine.mytestapplicationforskywebpro.ui.pictures

import androidx.lifecycle.LiveData
import androidx.paging.PagingData
import com.worldshine.mytestapplicationforskywebpro.data.PicturesRepository
import com.worldshine.mytestapplicationforskywebpro.model.PicturesResponse
import moxy.InjectViewState
import moxy.MvpPresenter


@InjectViewState
class PicturesFragmentPresenter : MvpPresenter<PicturesFragmentView>() {

    private val picturesRepository = PicturesRepository()
    fun getPictures(): LiveData<PagingData<PicturesResponse>> {
        return picturesRepository.getResultAsLiveData()
    }
}