package com.brujiyaseer.shoppingapp.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.brujiyaseer.shoppingapp.core.base.ListItem
import com.brujiyaseer.shoppingapp.core.utils.Resource
import com.brujiyaseer.shoppingapp.domain.model.FlashSale
import com.brujiyaseer.shoppingapp.domain.model.GoodsSectionItem
import com.brujiyaseer.shoppingapp.domain.model.Latest
import com.brujiyaseer.shoppingapp.domain.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import javax.inject.Inject

//private const val TAG = "MainViewModel"

@HiltViewModel
class MainViewModel @Inject constructor(private val repository: Repository) : ViewModel() {

    private val _homeGoodsLiveData = MutableLiveData<Resource<List<ListItem>>>()
    val homeGoodsLiveData: LiveData<Resource<List<ListItem>>>
        get() = _homeGoodsLiveData

    init {
        getGoodsList()
    }

    private fun getGoodsList() = viewModelScope.launch(Dispatchers.IO) {

        _homeGoodsLiveData.postValue(Resource.Loading)

        val latestGoodsDeferred = async { repository.getLatestGoods() }
        val flashSaleGoodsDeferred = async { repository.getFlashSaleGoods() }

        val latestGoods = latestGoodsDeferred.await()
        val flashSaleGoods = flashSaleGoodsDeferred.await()

//        Log.d(TAG, "goods are $latestGoods and $flashSaleGoods")

        if (latestGoods is Resource.Success && flashSaleGoods is Resource.Success) {
            val latestGoodsResponse = latestGoods.value.map {
                Latest(
                    category = it.category,
                    image_url = it.image_url,
                    name = it.name,
                    price = it.price
                )
            }
            val flashSaleGoodsResponse = flashSaleGoods.value.map {
                FlashSale(
                    category = it.category,
                    image_url = it.image_url,
                    name = it.name,
                    price = it.price,
                    discount = it.discount
                )
            }
            val goodsSectionResponse = listOf(
                GoodsSectionItem(
                    title = "Latest",
                    goods = latestGoodsResponse
                ),
                GoodsSectionItem(
                    title = "Flash Sale",
                    goods = flashSaleGoodsResponse
                ),
                GoodsSectionItem(
                    title = "Brands",
                    goods = latestGoodsResponse
                )
            )
            _homeGoodsLiveData.postValue(Resource.Success(goodsSectionResponse))
        } else {
            _homeGoodsLiveData.postValue(latestGoods as Resource.Failure)
        }

    }


}