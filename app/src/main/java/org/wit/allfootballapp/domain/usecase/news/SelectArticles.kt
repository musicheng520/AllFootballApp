package org.wit.allfootballapp.domain.usecase.news

import kotlinx.coroutines.flow.Flow
import org.wit.allfootballapp.domain.model.news.Article
import org.wit.allfootballapp.domain.repository.NewsRepository



class SelectArticles (private val newsRepository: NewsRepository
) {

     operator fun invoke(): Flow<List<Article>>{
      return  newsRepository.selectArticles()
    }

}