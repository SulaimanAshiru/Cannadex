package com.example.cannadex.data.api

import com.example.cannadex.data.model.StrainsResponse
import retrofit2.Response
import retrofit2.http.GET

interface CannabisService {

    @GET("strains")
    suspend fun getStrains(): Response<StrainsResponse>
}