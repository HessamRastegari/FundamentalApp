package com.hessam.newsapiclient.data.repository.dataSource

import com.hessam.newsapiclient.data.model.Article

interface NewsLocalDataSource {
    suspend fun saveArticleToDB(article:Article)
}