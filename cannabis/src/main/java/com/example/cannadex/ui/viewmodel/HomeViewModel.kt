package com.example.cannadex.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cannadex.data.model.StrainsResponse
import com.example.cannadex.data.repo.CannabisRepo
import com.example.cannadex.utils.Resource
import com.example.cannadex.utils.extensions.successWithData
import kotlinx.coroutines.launch
import retrofit2.Response

class HomeViewModel : ViewModel() {

    val strainsAlreadyLoaded: Boolean
        get() = currentStrainsPage > 0
    private val _strains = MutableLiveData<Resource<StrainsResponse>>()
    val strainsObservable: LiveData<Resource<StrainsResponse>>
        get() = _strains

    private var currentStrainsPage = 0
    private var maxStrainPage = 0

    init {
        fetchStrains()
    }

    private fun fetchStrains(url: String = "", pageNum: Int? = null) {
        toggleLoading(_strains)
        viewModelScope.launch {
            val response = when {
                url.isNotEmpty() -> CannabisRepo.getInstance().getStrains(url)
                pageNum != null -> CannabisRepo.getInstance().getStrains(pageNum)
                else -> CannabisRepo.getInstance().getStrains()
            }

            updateStrainPage(response)
            handleResponse(_strains, response)
        }
    }

    private fun updateStrainPage(response: Response<StrainsResponse>) {
        // we want to know MAX PAGE and CURRENT PAGE
        // This method will update current page and max page
        response.body()?.meta?.pagination?.let {
            it.currentPage?.let { currentStrainsPage = it }
            it.totalPages?.let { maxStrainPage = it }
        }
    }

    fun loadMoreUsingUrl() {
        _strains.value?.data?.meta?.pagination?.links?.next?.let { url ->
            if (url.isNotEmpty()) fetchStrains(url)
        }
    }

    fun loadMoreUsingPageNumber() {
        if (currentStrainsPage.plus(1) <= maxStrainPage) {
            fetchStrains(pageNum = currentStrainsPage.plus(1))
        }
    }

    private fun <T> toggleLoading(mutableLiveData: MutableLiveData<Resource<T>>) {
        mutableLiveData.value = Resource.loading()
    }

    private fun <T> handleResponse(
        mutableLiveData: MutableLiveData<Resource<T>>,
        response: Response<T>
    ) {
        val resource = when {
            response.successWithData() -> Resource.success(response.body())
            else -> Resource.error("Something went wrong: ${response.message()}")
        }
        mutableLiveData.postValue(resource)
    }
}