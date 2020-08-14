package com.example.cannadex.data.api

import com.example.cannadex.utils.ForceToGeneticsJsonAdapter
import com.example.cannadex.utils.ForceToLineageJsonAdapter
import com.example.cannadex.utils.ForceToListJsonAdapter
import com.example.cannadex.utils.ForceToStringJsonAdapter
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object RetrofitInstance {

    private val client by lazy {
        HttpLoggingInterceptor()
            .apply { level = HttpLoggingInterceptor.Level.BODY }
            .let { OkHttpClient.Builder().addInterceptor(it).build() }
    }

    private val moshi by lazy {
        Moshi.Builder()
            .add(ForceToGeneticsJsonAdapter())
            .add(ForceToLineageJsonAdapter())
            .add(ForceToStringJsonAdapter())
            .add(ForceToListJsonAdapter())
            .build()
    }

    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl("https://api.otreeba.com/v1/")
            .client(client)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .build()
    }

    val cannabisService: CannabisService by lazy {
        retrofit.create(CannabisService::class.java)
    }
}