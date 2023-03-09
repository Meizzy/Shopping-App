package com.brujiyaseer.shoppingapp.di

import android.app.Application
import androidx.room.Room
import com.brujiyaseer.shoppingapp.data.local.AppDatabase
import com.brujiyaseer.shoppingapp.data.local.DATABASE_NAME
import com.brujiyaseer.shoppingapp.data.remote.GoodsApi
import com.brujiyaseer.shoppingapp.data.repository.RepositoryImpl
import com.brujiyaseer.shoppingapp.domain.repository.Repository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideRepository(goodsApi: GoodsApi, db: AppDatabase): Repository {
        return RepositoryImpl(goodsApi, db.getUserDao)
    }

    @Singleton
    @Provides
    fun provideDataBase(app: Application): AppDatabase {
        return Room.databaseBuilder(app, AppDatabase::class.java, DATABASE_NAME).build()
    }

    @Singleton
    @Provides
    fun provideGoodsApi(): GoodsApi {
        return Retrofit.Builder().baseUrl(GoodsApi.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create()).build().create(GoodsApi::class.java)
    }
}