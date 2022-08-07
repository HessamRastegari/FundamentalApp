package com.hessam.newsapiclient.presentation.di

import com.hessam.newsapiclient.domain.repository.NewsRepository
import com.hessam.newsapiclient.domain.usecase.GetNewsHeadlinesUseCase
import com.hessam.newsapiclient.domain.usecase.GetSearchedNewsUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class UseCaseModule {

    @Singleton
    @Provides
    fun provideGetNewsHeadLinesUseCase(
        newsRepository: NewsRepository
    ): GetNewsHeadlinesUseCase{
        return GetNewsHeadlinesUseCase(newsRepository)
    }


    @Singleton
    @Provides
    fun provideGetSearchedNewsUseCase(
        newsRepository: NewsRepository
    ): GetSearchedNewsUseCase{
        return GetSearchedNewsUseCase(newsRepository)
    }
}