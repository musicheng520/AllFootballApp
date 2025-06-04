package org.wit.allfootballapp.data.remote.dto.news

import org.wit.allfootballapp.domain.model.news.Article


data class NewsResponse(
    val articles: List<Article>,
    val status: String,
    val totalResults: Int
)