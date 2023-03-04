package com.brujiyaseer.shoppingapp.data.remote

import com.brujiyaseer.shoppingapp.data.remote.dto.*
import retrofit2.http.GET

interface GoodsApi {

    @GET("v3/cc0071a1-f06e-48fa-9e90-b1c2a61eaca7")
    suspend fun getLatestList(): LatestHeader

    @GET("v3/a9ceeb6e-416d-4352-bde6-2203416576ac")
    suspend fun getFlashSaleList(): FlashSaleHeader

    @GET("/v3/4c9cd822-9479-4509-803d-63197e5a9e19")
    suspend fun getSearchList(): List<SearchDto>

    companion object {
        const val BASE_URL = "https://run.mocky.io/"
    }

}