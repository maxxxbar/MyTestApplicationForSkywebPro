package com.worldshine.mytestapplicationforskywebpro.network

import com.worldshine.mytestapplicationforskywebpro.model.ForecastResponse
import com.worldshine.mytestapplicationforskywebpro.model.PicturesResponse
import io.reactivex.Observable
import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface Rest {
    companion object {
        private const val PAGE_QUERY = "page"
        private const val CITY_QUERY = "id"
        private const val LANG_QUERY = "lang"
        private const val UNITS_QUERY = "units"
        private const val LANG_VALUE = "ru"
        private const val UNITS_VALUE = "metric"
        private const val CITY_VALUE = 498817
    }

    @GET("/v2/list")
    suspend fun getPictures(@Query(value = PAGE_QUERY) page: Int): Response<List<PicturesResponse>>

    @GET("/data/2.5/weather?")
    fun getWeather(
        @Query(CITY_QUERY) cityId: Int = CITY_VALUE,
        @Query(LANG_QUERY) lang: String = LANG_VALUE,
        @Query(UNITS_QUERY) units: String = UNITS_VALUE,
    ): Observable<ForecastResponse>
}