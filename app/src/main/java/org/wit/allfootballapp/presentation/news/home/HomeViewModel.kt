package org.wit.allfootballapp.presentation.news.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import dagger.hilt.android.lifecycle.HiltViewModel
import org.wit.allfootballapp.domain.usecase.news.NewsUseCases
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val newsUseCases: NewsUseCases
) : ViewModel(){
    val news = newsUseCases.getNews(
        sources = listOf("bbc-sport",
            "espn",
            "marca",
            "talksport",
            "four-four-two",
            "fox-sports",
            "bleacher-report",
            "the-sport-bible",
            "independent",
            "mirror")
    ).cachedIn(viewModelScope)
}