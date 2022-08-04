package com.hessam.newsapiclient.domain.usecase

import com.hessam.newsapiclient.domain.repository.NewsRepository

class GetSavedNewsUseCase(private val newsRepository: NewsRepository) {
}