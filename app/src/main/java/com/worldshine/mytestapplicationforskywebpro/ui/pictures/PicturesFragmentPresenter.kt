package com.worldshine.mytestapplicationforskywebpro.ui.pictures

import androidx.lifecycle.LiveData
import androidx.paging.PagingData
import com.worldshine.mytestapplicationforskywebpro.data.PicturesRepository
import com.worldshine.mytestapplicationforskywebpro.model.PicturesResponse
import com.worldshine.mytestapplicationforskywebpro.network.Connection
import moxy.MvpPresenter

class PicturesFragmentPresenter() : MvpPresenter<PicturesFragmentView>() {
    private val rest = Connection.getPicturesWithRetrofit()
    private val picturesRepository = PicturesRepository(rest)
    fun getPictures(): LiveData<PagingData<PicturesResponse>> {
        return picturesRepository.getResultAsLiveData()
    }
}