package com.worldshine.mytestapplicationforskywebpro.model

import com.google.gson.annotations.SerializedName

data class PicturesResponse(
    @field:SerializedName("id") val id: Int,
    @field:SerializedName("author") val author: String,
    @field:SerializedName("width") val width: Int,
    @field:SerializedName("height") val height: Int,
    @field:SerializedName("url") val url: String,
    @field:SerializedName("download_url") val download_url: String
)