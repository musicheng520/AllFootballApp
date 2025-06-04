package org.wit.allfootballapp.data.remote

import org.wit.allfootballapp.data.remote.dto.news.NewsResponse
import org.wit.allfootballapp.util.Constants.NEWS_API_KEY
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApi {
    @GET("everything")
    suspend fun getNews(
        @Query("page") page: Int,
        @Query("sources") sources: String,
        @Query("apiKey") apiKey: String = NEWS_API_KEY
    ): NewsResponse

    @GET("everything")
    suspend fun searchNews(
        @Query("q") searchQuery: String,
        @Query("page") page: Int,
        @Query("sources") sources: String,
        @Query("apiKey") apiKey: String =NEWS_API_KEY
    ): NewsResponse
}