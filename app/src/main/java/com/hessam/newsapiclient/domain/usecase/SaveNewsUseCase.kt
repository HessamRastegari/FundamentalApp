package com.hessam.newsapiclient.domain.usecase

import com.hessam.newsapiclient.data.model.APIResponse
import com.hessam.newsapiclient.data.model.Article
import com.hessam.newsapiclient.data.util.Resource
import com.hessam.newsapiclient.domain.repository.NewsRepository

class SaveNewsUseCase(private val newsRepository: NewsRepository) {


    suspend fun exceute(article: Article) = newsRepository.saveNews(article)

}