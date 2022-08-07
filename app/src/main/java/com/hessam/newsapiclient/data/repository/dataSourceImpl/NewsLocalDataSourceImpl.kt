package com.hessam.newsapiclient.data.repository.dataSourceImpl

import com.hessam.newsapiclient.data.db.ArticleDAO
import com.hessam.newsapiclient.data.model.Article
import com.hessam.newsapiclient.data.repository.dataSource.NewsLocalDataSource

class NewsLocalDataSourceImpl(
    private val articleDAO: ArticleDAO
):NewsLocalDataSource {
    override suspend fun saveArticleToDB(article: Article) {
        articleDAO.insert(article)
    }
}