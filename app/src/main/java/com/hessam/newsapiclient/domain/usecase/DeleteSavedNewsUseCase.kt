package com.hessam.newsapiclient.domain.usecase

import com.hessam.newsapiclient.domain.repository.NewsRepository

class DeleteSavedNewsUseCase(private val newsRepository: NewsRepository) {
}