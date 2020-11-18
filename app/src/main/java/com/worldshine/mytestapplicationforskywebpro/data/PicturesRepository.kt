package com.worldshine.mytestapplicationforskywebpro.data

import androidx.lifecycle.LiveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.liveData
import com.worldshine.mytestapplicationforskywebpro.model.PicturesResponse

class PicturesRepository() {
    private val pagingSourceFactory = { PicturesDataSource() }
    private val config = PagingConfig(pageSize = 20)
    fun getResultAsLiveData(): LiveData<PagingData<PicturesResponse>> {
        return Pager(
            config = config,
            pagingSourceFactory = pagingSourceFactory
        ).liveData
    }
}