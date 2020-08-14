package com.example.cannadex.data.api

class CannabisServiceImpl : CannabisService {

    companion object {
        private var INSTANCE: CannabisService? = null
        fun getInstance() = INSTANCE ?: CannabisServiceImpl().also { INSTANCE = it }
    }

    override suspend fun getStrains(count: Int) = RetrofitInstance.cannabisService.getStrains(count)

    override suspend fun getStrains(pageNum: Int, count: Int) =
        RetrofitInstance.cannabisService.getStrains(pageNum, count)

    override suspend fun getStrains(url: String, count: Int) = RetrofitInstance.cannabisService.getStrains(url, count)
}