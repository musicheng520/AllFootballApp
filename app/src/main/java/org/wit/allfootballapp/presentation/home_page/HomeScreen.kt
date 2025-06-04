package org.wit.allfootballapp.presentation.home_page.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import kotlinx.coroutines.launch
import org.wit.allfootballapp.presentation.home_page.info.InfoScreen

import org.wit.allfootballapp.presentation.home_page.transfer.TransferScreen
import org.wit.allfootballapp.presentation.home_page.worker.WorkerScreen

@Composable
fun HomeScreen(teamId: Int, navController: NavHostController,rootNavController: NavController) {
    val tabs = listOf("Info", "Player", "transfer")
    val pagerState = rememberPagerState(pageCount = { tabs.size })
    val coroutineScope = rememberCoroutineScope()
    val seasonId = 2023


    Column(modifier = Modifier.fillMaxSize()) {
        TabRow(selectedTabIndex = pagerState.currentPage) {
            tabs.forEachIndexed { index, title ->
                Tab(
                    selected = pagerState.currentPage == index,
                    onClick = {
                        coroutineScope.launch {
                            pagerState.animateScrollToPage(index)  // 点击切换页面
                        }
                    },
                    text = { Text(title) }
                )
            }
        }

        HorizontalPager(
            state = pagerState,
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
        ) { page ->
            when (page) {
                0 -> InfoScreen(teamId, onLogout = {


                    rootNavController.navigate("login_screen") {
                        popUpTo("info_screen") { inclusive = true }
                    }
                })
                1 -> WorkerScreen(teamId, onPlayerClick = {playerId->
                    navController.navigate("playerDetail/$playerId/$seasonId")
                })
                2 -> TransferScreen(teamId)
            }
        }
    }
}





