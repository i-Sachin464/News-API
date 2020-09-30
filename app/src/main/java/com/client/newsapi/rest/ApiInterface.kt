package com.client.newsapi.rest

import com.client.newsapi.model.NewsResponse
import com.client.newsapi.model.SourcesResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.QueryMap

interface ApiInterface {

    //    https://newsapi.org/v2/top-headlines?country=us&apiKey=API_KEY
//    https://newsapi.org/v2/top-headlines?sources=bbc-news&apiKey=API_KEY
//    https://newsapi.org/v2/top-headlines?country=de&category=business&apiKey=API_KEY
//    https://newsapi.org/v2/top-headlines?q=trump&apiKey=API_KEY
    @GET("v2/top-headlines")
    fun getHeadlines(@QueryMap queryMap: Map<String, String>): Call<NewsResponse>


    //    https://newsapi.org/v2/everything?q=bitcoin&apiKey=API_KEY
//    https://newsapi.org/v2/everything?q=apple&from=2020-09-29&to=2020-09-29&sortBy=popularity&apiKey=API_KEY
//    https://newsapi.org/v2/everything?domains=techcrunch.com,thenextweb.com&apiKey=API_KEY
    @GET("v2/everything")
    fun getEverything(@QueryMap queryMap: Map<String, String>): Call<NewsResponse>


    //    https://newsapi.org/v2/sources?apiKey=API_KEY
//    https://newsapi.org/v2/sources?language=en&apiKey=API_KEY
//    https://newsapi.org/v2/sources?language=en&country=us&apiKey=API_KEY
    @GET("v2/sources")
    fun getSources(@QueryMap queryMap: Map<String, String>): Call<SourcesResponse>


//    @GET("movie/{id}")
//    fun getNews(@Path("id") id: Int, @Query("api_key") apiKey: String): Call<List<News>>
}
