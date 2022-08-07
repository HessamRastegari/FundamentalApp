package com.hessam.newsapiclient.data.repository.dataSource

import com.hessam.newsapiclient.data.model.Article
import kotlinx.coroutines.flow.Flow

interface NewsLocalDataSource {
    suspend fun saveArticleToDB(article:Article)

    fun getSavedArticles(): Flow<List<Article>>
    suspend fun deleteArticlesFromDb(article: Article)
}