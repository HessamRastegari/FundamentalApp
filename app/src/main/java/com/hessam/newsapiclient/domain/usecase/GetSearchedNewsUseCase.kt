package com.hessam.newsapiclient.domain.usecase

import com.hessam.newsapiclient.data.model.APIResponse
import com.hessam.newsapiclient.data.util.Resource
import com.hessam.newsapiclient.domain.repository.NewsRepository

class GetSearchedNewsUseCase(private val newsRepository: NewsRepository) {

    suspend fun exceute(country : String,searchedQuery:String,page: Int): Resource<APIResponse> {
        return newsRepository.getSearchedNews(country, searchedQuery, page)
    }
}