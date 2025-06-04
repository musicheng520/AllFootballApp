package org.wit.allfootballapp.presentation.news.news_navigator

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.collectAsLazyPagingItems
import org.wit.allfootballapp.domain.model.news.Article
import org.wit.allfootballapp.presentation.news.bookmark.BookmarkScreen
import org.wit.allfootballapp.presentation.news.bookmark.BookmarkViewModel
import org.wit.allfootballapp.presentation.news.home.HomeViewModel
import org.wit.allfootballapp.presentation.news.home.NewsHomeScreen
import org.wit.allfootballapp.presentation.news.search.SearchScreen
import org.wit.allfootballapp.presentation.news.search.SearchViewModel


@Composable
fun TabScreen(
    onNavigateToDetails: (Article) -> Unit
) {
    // Tab标签列表
    val tabs = listOf("Home", "Search", "Bookmark")

    // 当前选中的tab索引
    var selectedTabIndex by remember { mutableStateOf(0) }

    // 下面的ViewModel分别对应你的屏幕，可以用hiltViewModel()或者从外部传入
    val homeViewModel: HomeViewModel = hiltViewModel()
    val searchViewModel: SearchViewModel = hiltViewModel()
    val bookmarkViewModel: BookmarkViewModel = hiltViewModel()

    Surface(
        modifier = Modifier.Companion.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column {

            // TabRow
            TabRow(selectedTabIndex = selectedTabIndex) {
                tabs.forEachIndexed { index, title ->
                    Tab(
                        selected = selectedTabIndex == index,
                        onClick = { selectedTabIndex = index },
                        text = { Text(text = title) }
                    )
                }
            }

            // 根据选中的tab显示对应页面
            when (selectedTabIndex) {
                0 -> {
                    val articles = homeViewModel.news.collectAsLazyPagingItems()
                    NewsHomeScreen(
                        articles = articles,
                        navigateToDetails = onNavigateToDetails,
                        navigateToSearch = {
                            // 如果需要跳转tab，可以控制selectedTabIndex或者用其他逻辑
                            selectedTabIndex = 1
                        }
                    )
                }

                1 -> {
                    val state = searchViewModel.state.value
                    SearchScreen(
                        state = state,
                        event = searchViewModel::onEvent,
                        navigateToDetails = onNavigateToDetails
                    )
                }

                2 -> {
                    val state = bookmarkViewModel.state.value
                    BookmarkScreen(
                        state = state,
                        navigateToDetails = onNavigateToDetails
                    )
                }
            }
        }
    }
}