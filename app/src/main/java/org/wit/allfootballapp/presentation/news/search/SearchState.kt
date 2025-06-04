package org.wit.allfootballapp.presentation.news.search

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import org.wit.allfootballapp.domain.model.news.Article


data class SearchState (
    val searchQuery: String ="",
    val articles:Flow<PagingData<Article>>?=null
)