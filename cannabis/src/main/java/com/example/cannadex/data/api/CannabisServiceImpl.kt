package com.example.cannadex.data.api

class CannabisServiceImpl : CannabisService {

    companion object {
        private var INSTANCE: CannabisService? = null
        fun getInstance() = INSTANCE ?: CannabisServiceImpl().also { INSTANCE = it }
    }

    override suspend fun getStrains() = RetrofitInstance.cannabisService.getStrains()

    override suspend fun getStrains(pageNum: Int) =
        RetrofitInstance.cannabisService.getStrains(pageNum)

    override suspend fun getStrains(url: String) = RetrofitInstance.cannabisService.getStrains(url)
}