package com.hessam.newsapiclient.data.repository.dataSourceImpl

import com.hessam.newsapiclient.data.db.ArticleDAO
import com.hessam.newsapiclient.data.model.Article
import com.hessam.newsapiclient.data.repository.dataSource.NewsLocalDataSource
import kotlinx.coroutines.flow.Flow

class NewsLocalDataSourceImpl(
    private val articleDAO: ArticleDAO
):NewsLocalDataSource {
    override suspend fun saveArticleToDB(article: Article) {
        articleDAO.insert(article)
    }

    override fun getSavedArticles(): Flow<List<Article>> {
        return articleDAO.getAllArticles()
    }

    override suspend fun deleteArticlesFromDb(article: Article) {
        articleDAO.deleteArticle(article)
    }


}