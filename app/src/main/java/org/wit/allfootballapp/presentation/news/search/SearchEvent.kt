package org.wit.allfootballapp.presentation.news.search

sealed class SearchEvent {

    data class UpdateSearchQuery(val searchQuery: String): SearchEvent()

    object SearchNews: SearchEvent()
}