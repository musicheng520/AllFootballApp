package org.wit.allfootballapp.presentation.home_page.worker.player_detail

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import org.wit.allfootballapp.presentation.home_page.worker.player_detail.components.PlayerInfoSection
import org.wit.allfootballapp.presentation.home_page.worker.player_detail.components.SeasonSelector
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.ui.text.style.TextAlign
import org.wit.allfootballapp.presentation.home_page.worker.player_detail.components.PlayerStatBarSection

import kotlin.math.min

@Composable
fun PlayerDetailScreen(
    playerId: Int,
    seasonId: Int,
    navController: NavController,
    viewModel: PlayerDetailViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsState()
    val scrollState = rememberScrollState()

    LaunchedEffect(playerId, seasonId) {
        viewModel.onEvent(PlayerDetailEvent.LoadPlayer(playerId, seasonId))
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(scrollState),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        when {
            state.isLoading -> {
                CircularProgressIndicator(modifier = Modifier.padding(16.dp))
            }

            state.error != null -> {
                Text(text = "Error: ${state.error}", color = MaterialTheme.colorScheme.error)
                Log.d("ERROR_MESSAGE", "${state.error}")
            }

            state.player != null -> {
                // 玩家信息：拉满、居中
                PlayerInfoSection(
                    player = state.player!!.player,
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(16.dp))

                // ✅ 移动了 SeasonSelector 到这里，居中 & fillMaxWidth
                SeasonSelector(
                    currentSeason = seasonId,
                    modifier = Modifier.fillMaxWidth()
                ) { selectedSeason ->
                    navController.navigate("playerDetail/$playerId/$selectedSeason") {
                        popUpTo("playerDetail/$playerId/$seasonId") { inclusive = true }
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                // 暂时留空，下一步处理统计数据
                // 替换原来的统计展示
                PlayerStatBarSection(statistics = state.player!!.statistics)

            }
        }
    }
}



