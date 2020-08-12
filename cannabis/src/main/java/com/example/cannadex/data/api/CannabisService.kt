package com.example.cannadex.data.api

import com.example.cannadex.data.model.StrainsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Url

interface CannabisService {

    @GET("strains")
    suspend fun getStrains(): Response<StrainsResponse>

    @GET("strains")
    suspend fun getStrains(@Query("page") pageNum: Int): Response<StrainsResponse>

    @GET
    suspend fun getStrains(@Url url: String): Response<StrainsResponse>
}