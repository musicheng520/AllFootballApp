package org.wit.allfootballapp.presentation.home_page.worker.player_detail.components


import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.wit.allfootballapp.domain.model.playerInfo.PlayerStatistic

@Composable
fun PlayerStatisticsSection(statistics: List<PlayerStatistic>) {
    val totalGames = statistics.sumOf { it.gamesPlayed }
    val totalGoals = statistics.sumOf { it.goals }
    val totalAssists = statistics.sumOf { it.assists }

    Column {
        Text("Games Played: $totalGames", style = MaterialTheme.typography.bodyMedium)
        Text("Goals: $totalGoals", style = MaterialTheme.typography.bodyMedium)
        Text("Assists: $totalAssists", style = MaterialTheme.typography.bodyMedium)

        Spacer(modifier = Modifier.height(12.dp))

        statistics.forEach { stat ->
            Card(modifier = Modifier.padding(vertical = 4.dp)) {
                Column(modifier = Modifier.padding(8.dp)) {
                    Text(text = "Team: ${stat.teamName}", style = MaterialTheme.typography.bodyLarge)
                    Text(text = "Position: ${stat.position}")
                    Text(text = "Rating: ${stat.rating ?: "N/A"}")
                    Text(text = "Yellow Cards: ${stat.yellowCards}, Red Cards: ${stat.redCards}")
                }
            }
        }
    }
}
