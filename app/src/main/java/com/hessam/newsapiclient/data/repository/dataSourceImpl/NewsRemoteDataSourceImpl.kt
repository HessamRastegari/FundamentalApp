package com.hessam.newsapiclient.data.repository.dataSourceImpl

import com.hessam.newsapiclient.data.api.NewsApiService
import com.hessam.newsapiclient.data.model.APIResponse
import com.hessam.newsapiclient.data.repository.dataSource.NewsRemoteDataSource
import retrofit2.Response
import retrofit2.http.Query

class NewsRemoteDataSourceImpl(
    private val newsApiService: NewsApiService
):NewsRemoteDataSource {
    override suspend fun getTopHeadLines(country: String, page: Int): Response<APIResponse> {
        return newsApiService.getTopHeadlines(country,page)
    }

    override suspend fun getSearchedNews(
        country: String,
        searchQuery: String,
        page: Int
    ): Response<APIResponse> {
        return newsApiService.getSearchedTopHeadlines(country, searchQuery, page)
    }

}