package org.wit.allfootballapp.presentation.home_page.worker.player_detail.components

import PlayerStat
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import org.wit.allfootballapp.domain.model.playerInfo.PlayerStatistic

@Composable
fun PlayerStatBarSection(statistics: List<PlayerStatistic>) {
    // 数据处理
    val totalGoals = statistics.sumOf { it.goals }
    val totalAssists = statistics.sumOf { it.assists }
    val totalGames = statistics.sumOf { it.gamesPlayed }

    val averageRating = statistics
        .mapNotNull { it.rating?.toDoubleOrNull() }
        .average()
        .takeIf { !it.isNaN() } ?: 0.0

    Column(modifier = Modifier
        .fillMaxWidth()
        .padding(16.dp)) {
        Text("Player Stats Summary", style = MaterialTheme.typography.titleMedium)

        Spacer(modifier = Modifier.height(16.dp))

        PlayerStat(
            statName = "Goals",
            statValue = totalGoals,
            statMaxValue = 50,
            statColor = Color(0xFF4CAF50), // green
            animDelay = 0
        )
        Spacer(modifier = Modifier.height(12.dp))

        PlayerStat(
            statName = "Assists",
            statValue = totalAssists,
            statMaxValue = 20,
            statColor = Color(0xFF03A9F4), // blue
            animDelay = 200
        )
        Spacer(modifier = Modifier.height(12.dp))

        PlayerStat(
            statName = "Games Played",
            statValue = totalGames,
            statMaxValue = 60,
            statColor = Color(0xFFFFC107), // amber
            animDelay = 400
        )
        Spacer(modifier = Modifier.height(12.dp))

        PlayerStat(
            statName = "Rating",
            statValue = (averageRating * 10).toInt(), // e.g. 7.5 => 75
            statMaxValue = 100, // Rating out of 10 -> *10
            statColor = Color(0xFFFF5722), // deep orange
            animDelay = 600
        )
    }
}
