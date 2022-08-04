package com.hessam.newsapiclient.domain.usecase

import com.hessam.newsapiclient.domain.repository.NewsRepository

class GetSearchedNewsUseCase(private val newsRepository: NewsRepository) {
}