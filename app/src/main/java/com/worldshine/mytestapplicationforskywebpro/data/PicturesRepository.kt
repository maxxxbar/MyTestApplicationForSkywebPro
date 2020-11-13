package com.worldshine.mytestapplicationforskywebpro.data

import androidx.lifecycle.LiveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.liveData
import com.worldshine.mytestapplicationforskywebpro.model.PicturesResponse
import com.worldshine.mytestapplicationforskywebpro.network.Rest

class PicturesRepository(private val rest: Rest) {
    fun getResultAsLiveData(): LiveData<PagingData<PicturesResponse>> {
        return Pager(
            config = PagingConfig(pageSize = 20),
            pagingSourceFactory = { PicturesDataSource(rest) }
        ).liveData
    }
}