package org.wit.allfootballapp.presentation.news.bookmark

import org.wit.allfootballapp.domain.model.news.Article


data class BookmarkState(
    val articles: List<Article> = emptyList()
)
