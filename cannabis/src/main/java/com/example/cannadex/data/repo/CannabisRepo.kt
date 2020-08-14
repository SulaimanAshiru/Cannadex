package com.example.cannadex.data.repo

import com.example.cannadex.data.api.CannabisServiceImpl

class CannabisRepo {

    companion object {
        private const val COUNT = 50
        private var INSTANCE: CannabisRepo? = null
        fun getInstance() = INSTANCE ?: CannabisRepo().also { INSTANCE = it }
    }

    suspend fun getStrains() = CannabisServiceImpl.getInstance().getStrains(COUNT)

    suspend fun getStrains(url: String) = CannabisServiceImpl.getInstance().getStrains(url, COUNT)

    suspend fun getStrains(pageNum: Int) = CannabisServiceImpl.getInstance().getStrains(pageNum, COUNT)
}