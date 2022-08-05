package com.hessam.newsapiclient.data.repository.dataSource

import com.hessam.newsapiclient.data.model.APIResponse
import retrofit2.Response

interface NewsRemoteDataSource {
    suspend fun getTopHeadLines(): Response<APIResponse>
}