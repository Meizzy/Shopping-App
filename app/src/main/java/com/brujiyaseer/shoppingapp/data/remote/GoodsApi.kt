package com.brujiyaseer.shoppingapp.data.remote

import com.brujiyaseer.shoppingapp.data.remote.dto.*
import retrofit2.http.GET

interface GoodsApi {

    @GET("v3/cc0071a1-f06e-48fa-9e90-b1c2a61eaca7")
    suspend fun getLatestList(): LatestHeader

    @GET("v3/a9ceeb6e-416d-4352-bde6-2203416576ac")
    suspend fun getFlashSaleList(): FlashSaleHeader

    @GET("/v3/4c9cd822-9479-4509-803d-63197e5a9e19")
    suspend fun getSearchList(): SearchDto

    @GET("v3/f7f99d04-4971-45d5-92e0-70333383c239")
    suspend fun getDetailsList(): DetailsDto

    companion object {
        const val BASE_URL = "https://run.mocky.io/"
    }

}