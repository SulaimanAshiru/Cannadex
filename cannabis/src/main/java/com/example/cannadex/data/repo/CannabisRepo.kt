package com.example.cannadex.data.repo

import com.example.cannadex.data.api.CannabisServiceImpl

class CannabisRepo {

    companion object {
        private var INSTANCE: CannabisRepo? = null
        fun getInstance() = INSTANCE ?: CannabisRepo().also { INSTANCE = it }
    }

    suspend fun getStrains() = CannabisServiceImpl.getInstance().getStrains()
}