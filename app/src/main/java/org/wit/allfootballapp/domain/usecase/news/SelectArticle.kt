package org.wit.allfootballapp.domain.usecase.news

import org.wit.allfootballapp.domain.model.news.Article
import org.wit.allfootballapp.domain.repository.NewsRepository



class SelectArticle(
    private val newsRepository: NewsRepository
) {

    suspend operator fun invoke(url: String): Article?{
       return newsRepository.selectArticle(url = url)
    }

}