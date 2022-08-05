package com.hessam.newsapiclient.data.api

import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okio.buffer
import okio.source
import org.junit.After
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class NewsAPIServiceTest {

    private lateinit var service: NewsApiService
    private lateinit var server: MockWebServer

    @Before
    fun setUp() {
        server = MockWebServer()
        service = Retrofit.Builder()
            .baseUrl(server.url(""))
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(NewsApiService::class.java)
    }


    private fun enqueueMockResponse(
        fileName:String
    ){
        val inputStream = javaClass.classLoader!!.getResourceAsStream(fileName)
        val source = inputStream.source().buffer()
        val mockResponse = MockResponse()
        mockResponse.setBody(source.readString(Charsets.UTF_8))
        server.enqueue(mockResponse)
    }

    @Test
    fun getTopHeadlines_sentRequest_receivedExpected(){
        runBlocking {
            enqueueMockResponse("newsresponse.json")
            val responseBody = service.getTopHeadlines("us",1).body()
            val request = server.takeRequest()
            assertThat(responseBody).isNotNull()
            assertThat(request.path).isEqualTo("/v2/top-headlines?country=us&page=1&apiKey=e23918a5156d4a7ab54845bbcd092716")
        }
    }


     @Test
     fun getTopHeadlines_receiveResponse_correctPageSize(){
         runBlocking {
             enqueueMockResponse("newsresponse.json")
             val responseBody = service.getTopHeadlines("us",1).body()
             val articleList = responseBody!!.articles
             assertThat(articleList.size).isEqualTo(20)
         }
     }


    @Test
    fun getTopHeadlines_receiveResponse_correctCorrectContent(){
        runBlocking {
            enqueueMockResponse("newsresponse.json")
            val responseBody = service.getTopHeadlines("us",1).body()
            val articleList = responseBody!!.articles
            val article = articleList[0]
            assertThat(article.url).isEqualTo("https://www.reuters.com/business/media-telecom/mistrial-denied-jury-weighs-damages-against-alex-jones-sandy-hook-defamation-2022-08-04/")
            assertThat(article.publishedAt).isEqualTo("2022-08-05T05:35:00Z")
        }
    }








    @After
    fun tearDown() {
        server.shutdown()
    }
}