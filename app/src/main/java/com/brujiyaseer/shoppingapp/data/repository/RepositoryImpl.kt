package com.brujiyaseer.shoppingapp.data.repository

import androidx.lifecycle.LiveData
import com.brujiyaseer.shoppingapp.core.utils.Resource
import com.brujiyaseer.shoppingapp.core.utils.SafeApiCall
import com.brujiyaseer.shoppingapp.data.local.UserDao
import com.brujiyaseer.shoppingapp.data.local.entities.User
import com.brujiyaseer.shoppingapp.data.remote.GoodsApi
import com.brujiyaseer.shoppingapp.domain.model.Details
import com.brujiyaseer.shoppingapp.domain.model.FlashSale
import com.brujiyaseer.shoppingapp.domain.model.Latest
import com.brujiyaseer.shoppingapp.domain.model.Search
import com.brujiyaseer.shoppingapp.domain.repository.Repository

class RepositoryImpl(private val goodsApi: GoodsApi, private val dao: UserDao) : Repository,
    SafeApiCall {

    override suspend fun getLatestGoods(): Resource<List<Latest>> =
        safeApiCall { goodsApi.getLatestList().latest.map { it.toLatest() } }

    override suspend fun getFlashSaleGoods(): Resource<List<FlashSale>> =
        safeApiCall { goodsApi.getFlashSaleList().flash_sale.map { it.toFlashSale() } }

    override suspend fun getSearchedGoods(): Resource<Search> =
        safeApiCall { goodsApi.getSearchList().toSearch() }

    override suspend fun getDetailsOfGoods(): Resource<Details> =
        safeApiCall { goodsApi.getDetailsList().toDetails() }

    override suspend fun saveUser(user: User): Long = dao.updateAndInsert(user)

    override suspend fun deleteUser() = dao.deleteUser()

    override fun getUser(): LiveData<User> = dao.getUser()

}