package com.hessam.newsapiclient.domain.usecase

import com.hessam.newsapiclient.domain.repository.NewsRepository

class SaveNewsUseCase(private val newsRepository: NewsRepository) {
}