package com.brujiyaseer.shoppingapp.presentation.destinations.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.brujiyaseer.shoppingapp.core.utils.Resource
import com.brujiyaseer.shoppingapp.domain.model.Search
import com.brujiyaseer.shoppingapp.domain.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(private val repository: Repository) : ViewModel() {

    private val _searchListLiveData = MutableLiveData<Resource<Search>>()
    val searchListLiveData: LiveData<Resource<Search>>
        get() = _searchListLiveData

    fun getSearchList(word: String) = viewModelScope.launch {
        delay(1000L)
        _searchListLiveData.postValue(Resource.Loading)

        val searchedResult = withContext(Dispatchers.IO) {
            repository.getSearchedGoods()
        }

        val resultsList = ArrayList<String>()
        val foundResults = ArrayList<String>()

        if (searchedResult is Resource.Success) {
            resultsList.addAll(searchedResult.value.words)
            resultsList.forEach {
                if (it.contains(word, true)) foundResults.add(it)
            }
            _searchListLiveData.postValue(Resource.Success(Search(foundResults)))
        } else _searchListLiveData.postValue(searchedResult)
    }
}