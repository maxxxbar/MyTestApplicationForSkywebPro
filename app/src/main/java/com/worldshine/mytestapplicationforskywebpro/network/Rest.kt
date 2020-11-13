package com.worldshine.mytestapplicationforskywebpro.network

import com.worldshine.mytestapplicationforskywebpro.model.PicturesResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface Rest {
    @GET("/v2/list")
    suspend fun getPictures(@Query(value = "page") page: Int): Response<List<PicturesResponse>>
}