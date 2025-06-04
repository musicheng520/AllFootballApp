package org.wit.allfootballapp.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import org.wit.allfootballapp.data.local.news.NewsDao
import org.wit.allfootballapp.data.remote.NewsApi
import org.wit.allfootballapp.data.remote.NewsPagingSource
import org.wit.allfootballapp.data.remote.SearchNewsPagingSource
import org.wit.allfootballapp.domain.model.news.Article
import org.wit.allfootballapp.domain.repository.NewsRepository



import kotlin.collections.joinToString

class NewsRepositoryImpl(
    private val newsApi: NewsApi,
    private val newsDao: NewsDao
) : NewsRepository{
    override fun getNews(sources: List<String>): Flow<PagingData<Article>> {
        return Pager(
            config = PagingConfig(pageSize = 10),
            pagingSourceFactory = {
                NewsPagingSource(
                    newsApi = newsApi,
                    sources = sources.joinToString(separator = ",")
                )
            }
        ).flow
    }

    override fun searchNews(
        searchQuery: String,
        sources: List<String>
    ): Flow<PagingData<Article>> {
         return Pager(
             config = PagingConfig(pageSize = 10),
             pagingSourceFactory = {
                 SearchNewsPagingSource(
                     searchQuery = searchQuery,
                     newsApi = newsApi,
                     sources = sources.joinToString(separator = ",")
                 )
             }
         ).flow
    }

    override suspend fun upsertArticle(article: Article) {
        newsDao.upsert(article)
    }

    override suspend fun deleteArticle(article: Article) {
        newsDao.delete(article)
    }

    override fun selectArticles(): Flow<List<Article>> {
        return newsDao.getArticles()
    }

    override suspend fun selectArticle(url: String): Article? {
        return newsDao.getArticle(url)
    }
}