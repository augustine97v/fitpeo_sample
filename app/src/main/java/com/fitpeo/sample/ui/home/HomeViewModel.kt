package com.fitpeo.sample.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fitpeo.sample.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * @author Augustine on 01/03/23.
 * */
@HiltViewModel
class HomeViewModel
@Inject
constructor(private val repository: HomeRepository) : ViewModel() {


    // Login request
    private val _dataStateHomeRequest: MutableLiveData<Resource<List<HomeResponseNetworkEntity>>> =
        MutableLiveData()

    val dataStateHomeRequest: LiveData<Resource<List<HomeResponseNetworkEntity>>>
        get() = _dataStateHomeRequest

    fun setStateHomeData(
    ) {
        viewModelScope.launch {
            repository.getHomeRecyclerData()
                .onEach { dataState ->
                    _dataStateHomeRequest.value = dataState
                }
                .launchIn(viewModelScope)
        }
    }

}