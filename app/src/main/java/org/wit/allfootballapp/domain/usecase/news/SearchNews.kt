package org.wit.allfootballapp.domain.usecase.news

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import org.wit.allfootballapp.domain.model.news.Article
import org.wit.allfootballapp.domain.repository.NewsRepository



class SearchNews(
    private val newsRepository: NewsRepository
) {
    operator fun invoke(searchQuery: String, sources: List<String>): Flow<PagingData<Article>>{
        return newsRepository.searchNews(searchQuery = searchQuery,sources=sources)
    }
}