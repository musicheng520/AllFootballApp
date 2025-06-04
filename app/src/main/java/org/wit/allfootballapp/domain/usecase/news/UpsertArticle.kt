package org.wit.allfootballapp.domain.usecase.news

import org.wit.allfootballapp.domain.model.news.Article
import org.wit.allfootballapp.domain.repository.NewsRepository


class UpsertArticle(
   private val newsRepository: NewsRepository
) {

    suspend operator fun invoke(article: Article){
        newsRepository.upsertArticle(article)
    }

}