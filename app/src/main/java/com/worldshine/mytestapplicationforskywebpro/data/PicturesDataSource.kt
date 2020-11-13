package com.worldshine.mytestapplicationforskywebpro.data

import androidx.paging.PagingSource
import com.worldshine.mytestapplicationforskywebpro.model.PicturesResponse
import com.worldshine.mytestapplicationforskywebpro.network.Rest
import retrofit2.HttpException
import java.io.IOException

class PicturesDataSource(
    private val rest: Rest
) : PagingSource<Int, PicturesResponse>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, PicturesResponse> {
        val page = params.key ?: 1
        return try {
            val response = rest.getPictures(page)
            val q = response.body()
            val list = mutableListOf<PicturesResponse>()
            if (q != null) {
                list.addAll(q)
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
}