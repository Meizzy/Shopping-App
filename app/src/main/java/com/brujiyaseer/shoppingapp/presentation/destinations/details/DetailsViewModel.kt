package com.brujiyaseer.shoppingapp.presentation.destinations.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.brujiyaseer.shoppingapp.core.utils.Resource
import com.brujiyaseer.shoppingapp.domain.model.Details
import com.brujiyaseer.shoppingapp.domain.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(private val repository: Repository) : ViewModel() {

    private val _detailsLiveData = MutableLiveData<Resource<Details>>()
    val detailsLiveData: LiveData<Resource<Details>>
        get() = _detailsLiveData

    init {
        getDetails()
    }

    private fun getDetails() = viewModelScope.launch(Dispatchers.IO) {
        _detailsLiveData.postValue(Resource.Loading)

        val deferredDetails = withContext(Dispatchers.IO) {
            repository.getDetailsOfGoods()
        }

        if (deferredDetails is Resource.Success) {
            _detailsLiveData.postValue(deferredDetails)
        } else _detailsLiveData.postValue(deferredDetails)
    }

}