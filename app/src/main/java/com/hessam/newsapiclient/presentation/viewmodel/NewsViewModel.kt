package com.hessam.newsapiclient.presentation.viewmodel

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.util.Log
import androidx.lifecycle.*
import com.hessam.newsapiclient.data.model.APIResponse
import com.hessam.newsapiclient.data.model.Article
import com.hessam.newsapiclient.data.util.Resource
import com.hessam.newsapiclient.domain.usecase.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExecutorCoroutineDispatcher
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class NewsViewModel(
    private val app: Application,
    private val getNewsHeadlinesUseCase: GetNewsHeadlinesUseCase,
    private val getSearchedNewsUseCase: GetSearchedNewsUseCase,
    private val saveNewsUseCase: SaveNewsUseCase,
    private val getSavedNewsUseCase: GetSavedNewsUseCase,
private val deleteSavedNewsUseCase: DeleteSavedNewsUseCase): AndroidViewModel(app) {

    val newsHeadlines : MutableLiveData<Resource<APIResponse>> = MutableLiveData()

    fun getNewsHeadLines(country: String, page:Int) = viewModelScope.launch(Dispatchers.IO) {
        newsHeadlines.postValue(Resource.Loading())
        try {
            if (isNetworkAvailable(app)) {

                val apiResult = getNewsHeadlinesUseCase.exceute(country, page)
                newsHeadlines.postValue(apiResult)
            } else {
                newsHeadlines.postValue(Resource.Error("Internet is not connected"))
            }
        }catch(e:Exception){
                newsHeadlines.postValue(Resource.Error(e.message.toString()))
        }
    }

    private fun isNetworkAvailable(context: Context?): Boolean{
        if (context == null) return false
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if (connectivityManager != null) {
            val capabilities =
                connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
            if (capabilities != null) {
                if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)) {
                    Log.i("Internet", "NetworkCapabilities.TRANSPORT_CELLULAR")
                    return true
                } else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)) {
                    Log.i("Internet", "NetworkCapabilities.TRANSPORT_WIFI")
                    return true
                } else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET)) {
                    Log.i("Internet", "NetworkCapabilities.TRANSPORT_ETHERNET")
                    return true
                }
            }
        }
        return false
    }


    //search
    val searchedNews: MutableLiveData<Resource<APIResponse>> = MutableLiveData()


    fun searchNews(
        country: String,
        seachQuery: String,
        page: Int
    ) = viewModelScope.launch {
        searchedNews.postValue(Resource.Loading())
        try {
            if (isNetworkAvailable(app)) {
                val response = getSearchedNewsUseCase.exceute(
                    country,
                    seachQuery,
                    page
                )
                searchedNews.postValue(response)
            } else {
                searchedNews.postValue(Resource.Error("No Internet connection"))
            }
        }catch (e:Exception){
            searchedNews.postValue(Resource.Error(e.message.toString()))
        }
    }

    //local data
    fun saveArticle(article: Article) = viewModelScope.launch {
        saveNewsUseCase.exceute(article)
    }

    fun getSavedNews() = liveData {
        getSavedNewsUseCase.execute().collect{
            emit(it)
        }
    }

    fun deleteArticle(article: Article)= viewModelScope.launch {
        deleteSavedNewsUseCase.execute(article)

    }

}