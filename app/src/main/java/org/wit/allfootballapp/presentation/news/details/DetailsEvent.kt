package org.wit.allfootballapp.presentation.news.details

import org.wit.allfootballapp.domain.model.news.Article


sealed class DetailsEvent {

    data class UpsertDeleteArticle(val article: Article): DetailsEvent()

    object RemoveSideEffect: DetailsEvent()

}