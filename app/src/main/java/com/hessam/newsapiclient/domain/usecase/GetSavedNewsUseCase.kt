package com.hessam.newsapiclient.domain.usecase

import com.hessam.newsapiclient.data.model.Article
import com.hessam.newsapiclient.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow

class GetSavedNewsUseCase(private val newsRepository: NewsRepository) {
    fun execute(): Flow<List<Article>>{
        return newsRepository.getSavedNews()
    }
}