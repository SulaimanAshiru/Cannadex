package com.example.cannadex.data.repo

import com.example.cannadex.data.api.CannabisServiceImpl

class CannabisRepo {

    companion object {
        private var INSTANCE: CannabisRepo? = null
        fun getInstance() = INSTANCE ?: CannabisRepo().also { INSTANCE = it }
    }

    suspend fun getStrains() = CannabisServiceImpl.getInstance().getStrains()

    suspend fun getStrains(url: String) = CannabisServiceImpl.getInstance().getStrains(url)

    suspend fun getStrains(pageNum: Int) = CannabisServiceImpl.getInstance().getStrains(pageNum)
}