package com.brujiyaseer.shoppingapp.domain.repository

import androidx.lifecycle.LiveData
import com.brujiyaseer.shoppingapp.core.utils.Resource
import com.brujiyaseer.shoppingapp.data.local.entities.User
import com.brujiyaseer.shoppingapp.domain.model.Details
import com.brujiyaseer.shoppingapp.domain.model.FlashSale
import com.brujiyaseer.shoppingapp.domain.model.Latest
import com.brujiyaseer.shoppingapp.domain.model.Search

interface Repository {

    suspend fun getLatestGoods(): Resource<List<Latest>>
    suspend fun getFlashSaleGoods(): Resource<List<FlashSale>>
    suspend fun getSearchedGoods(): Resource<Search>
    suspend fun getDetailsOfGoods(): Resource<Details>
    suspend fun saveUser(user: User): Long
    suspend fun deleteUser()
    fun getUser(): LiveData<User>

}