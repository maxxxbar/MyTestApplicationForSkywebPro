package com.worldshine.mytestapplicationforskywebpro.data

import androidx.paging.PagingSource
import com.worldshine.mytestapplicationforskywebpro.di.component.DaggerNetworkComponent
import com.worldshine.mytestapplicationforskywebpro.di.module.NetworkModule
import com.worldshine.mytestapplicationforskywebpro.model.PicturesResponse
import com.worldshine.mytestapplicationforskywebpro.network.Rest
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class PicturesDataSource(
) : PagingSource<Int, PicturesResponse>() {

    @Inject
    lateinit var rest: Rest

    init {
        injectDependency()
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, PicturesResponse> {
        val page = params.key ?: 1
        return try {
            val response = rest.getPictures(page)
            val responseBody = response.body()
            val list = mutableListOf<PicturesResponse>()
            if (responseBody != null) {
                list.addAll(responseBody)
            }
            LoadResult.Page(
                data = list,
                prevKey = if (page == 1) null else page + 1,
                nextKey = if (list.isEmpty()) null else page + 1
            )

        } catch (e: IOException) {
            LoadResult.Error(e)
        } catch (e: HttpException) {
            LoadResult.Error(e)
        }
    }

    private fun injectDependency() {
        val networkComponent =
            DaggerNetworkComponent.builder().networkModule(NetworkModule("https://picsum.photos"))
        networkComponent.build().inject(this)
    }
}