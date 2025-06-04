package org.wit.allfootballapp.presentation.news.nvgraph

sealed class Route(
    val route: String
) {object OnBoardingScreen : Route(route = "onBoardingScreen")

    object HomeScreen : Route(route = "homeScreen")

    object SearchScreen : Route(route = "searchScreen")

    object BookmarkScreen : Route(route = "bookMarkScreen")

    object DetailsScreen : Route(route = "detailsScreen")

    object AppStartNavigation : Route(route = "appStartNavigation")

    object NewsNavigation : Route(route = "newsNavigation")

    object NewsNavigatorScreen : Route(route = "newsNavigator")

    // 新增 TabScreen 用于顶部Tab切换三个主屏
    object TabScreen : Route(route = "tabScreen")
}