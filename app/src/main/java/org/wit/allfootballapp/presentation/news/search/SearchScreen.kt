package org.wit.allfootballapp.presentation.news.search

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.paging.compose.collectAsLazyPagingItems
import org.wit.allfootballapp.domain.model.news.Article
import org.wit.allfootballapp.presentation.news.Dimensions.MediumPadding1
import org.wit.allfootballapp.presentation.news.common.ArticlesList
import org.wit.allfootballapp.presentation.news.common.SearchBar


@Composable
fun SearchScreen(
    state: SearchState,
    event: (SearchEvent)-> Unit,
    navigateToDetails:(Article)-> Unit
) {
    Column(modifier = Modifier
        .padding(
            top = MediumPadding1,
            start = MediumPadding1,
            end = MediumPadding1
        )
        .fillMaxSize()
        .statusBarsPadding())
    {
        SearchBar(text = state.searchQuery, readOnly = false, onValueChange = {
            event(SearchEvent.UpdateSearchQuery(it))
        }, onSearch = {event(SearchEvent.SearchNews)})

        Spacer(modifier = Modifier.height(MediumPadding1))


        state.articles?.let {
            val articles = it.collectAsLazyPagingItems()

            ArticlesList(articles = articles, onClick = {
                navigateToDetails(it)})

        }

    }
}