package org.wit.allfootballapp.presentation.home_page.worker

import android.util.Log
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import org.wit.allfootballapp.presentation.home_page.worker.coach.CoachEvent
import org.wit.allfootballapp.presentation.home_page.worker.coach.CoachSection
import org.wit.allfootballapp.presentation.home_page.worker.coach.CoachViewModel
import org.wit.allfootballapp.presentation.home_page.worker.player.PlayerEvent
import org.wit.allfootballapp.presentation.home_page.worker.player.PlayerListSection
import org.wit.allfootballapp.presentation.home_page.worker.player.PlayerViewModel

@Composable
fun WorkerScreen(
    teamId: Int,
    season: Int =2023,
    onPlayerClick: (Int) -> Unit,
    playerViewModel: PlayerViewModel = hiltViewModel(),
) {
    val playerState by playerViewModel.uiState.collectAsState()


    // Load player & coach data
    LaunchedEffect(teamId, season) {
        playerViewModel.onEvent(PlayerEvent.LoadPlayers(teamId))

    }

    Column(modifier = Modifier.fillMaxSize()) {
        // 教练 Section
        CoachSection(teamId)

        Spacer(modifier = Modifier.height(8.dp))

        // 球员 Section
        when {
            playerState.isLoading -> {
                CircularProgressIndicator(modifier = Modifier.padding(16.dp))
            }
            playerState.players != null -> {
                PlayerListSection(players = playerState.players, onPlayerClick = onPlayerClick)
            }
            playerState.error != null -> {
                Text(
                    text = "Player Error: ${playerState.error}",
                    color = MaterialTheme.colorScheme.error,
                    modifier = Modifier.padding(16.dp)
                )
                Log.d("ERROR_MESSAGE", "${playerState.error}")
            }
        }
    }
}
