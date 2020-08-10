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

    private val _strains = MutableLiveData<Resource<StrainsResponse>>()
    val strainsObservable: LiveData<Resource<StrainsResponse>>
        get() = _strains

    init {
        fetchStrains()
    }

    private fun fetchStrains() {
        toggleLoading(_strains)
        viewModelScope.launch {
            CannabisRepo.getInstance().getStrains().let { response ->

            }
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