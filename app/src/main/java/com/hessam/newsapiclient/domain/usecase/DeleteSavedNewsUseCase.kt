package com.hessam.newsapiclient.domain.usecase

import com.hessam.newsapiclient.data.model.Article
import com.hessam.newsapiclient.domain.repository.NewsRepository

class DeleteSavedNewsUseCase(private val newsRepository: NewsRepository) {
     suspend fun execute(article: Article)=newsRepository.deleteNews(article)
}