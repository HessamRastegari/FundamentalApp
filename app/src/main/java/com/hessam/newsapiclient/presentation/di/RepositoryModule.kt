package com.hessam.newsapiclient.presentation.di

import com.hessam.newsapiclient.data.repository.NewsRepositoryImpl
import com.hessam.newsapiclient.data.repository.dataSource.NewsRemoteDataSource
import com.hessam.newsapiclient.data.repository.dataSourceImpl.NewsRemoteDataSourceImpl
import com.hessam.newsapiclient.domain.repository.NewsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Singleton
    @Provides
    fun provideNewsRepository(
        newsRemoteDataSource: NewsRemoteDataSource
    ):NewsRepository{
        return NewsRepositoryImpl(newsRemoteDataSource)
    }
}