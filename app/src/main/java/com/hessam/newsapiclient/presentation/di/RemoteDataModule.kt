package com.hessam.newsapiclient.presentation.di

import com.hessam.newsapiclient.data.api.NewsApiService
import com.hessam.newsapiclient.data.repository.dataSource.NewsRemoteDataSource
import com.hessam.newsapiclient.data.repository.dataSourceImpl.NewsRemoteDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RemoteDataModule {

    @Singleton
    @Provides
    fun provideNewsRemoteDataSource(
        newsAPIService: NewsApiService
    ): NewsRemoteDataSource{
        return NewsRemoteDataSourceImpl(newsAPIService)
    }
}