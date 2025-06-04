package org.wit.allfootballapp.presentation.home_page.worker.player

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter
import org.wit.allfootballapp.R
import org.wit.allfootballapp.domain.model.playerList.PlayerWithStats

@Composable
fun PlayerListSection(
    players: List<PlayerWithStats>,
    onPlayerClick: (Int) -> Unit
) {
    if (players.isEmpty()) {
        // 空状态提示
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(32.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "No players found.",
                style = MaterialTheme.typography.bodyLarge
            )
        }
    } else {
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(8.dp),
            contentPadding = PaddingValues(vertical = 8.dp)
        ) {
            items(players, key = { it.player.id }) { playerWithStats ->
                val player = playerWithStats.player
                val totalGames = playerWithStats.statistics.sumOf { it.gamesPlayed }
                val totalGoals = playerWithStats.statistics.sumOf { it.goals }
                val totalAssists = playerWithStats.statistics.sumOf { it.assists }

                PlayerItem(
                    name = player.name,
                    age = player.age,
                    photoUrl = player.photoUrl,
                    appearances = totalGames,
                    goals = totalGoals,
                    assists = totalAssists,
                    onClick = { onPlayerClick(player.id) }
                )
            }
        }
    }
}

@Composable
fun PlayerItem(
    name: String,
    age: Int,
    photoUrl: String,
    appearances: Int,
    goals: Int,
    assists: Int,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 12.dp)
            .clickable { onClick() },
        shape = MaterialTheme.shapes.medium,
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Row(
            modifier = Modifier
                .padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // 左边头像
            AsyncImage(
                model = photoUrl,
                contentDescription = null,
                modifier = Modifier
                    .size(60.dp)
                    .clip(CircleShape),
                placeholder = painterResource(id = R.drawable.ic_time),
                error = painterResource(id = R.drawable.ic_network_error)
            )

            Spacer(modifier = Modifier.width(12.dp))

            // 中间信息
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(text = name, style = MaterialTheme.typography.titleMedium)
                Text(text = "Age: $age", style = MaterialTheme.typography.bodySmall)
            }

            // 右边数据
            Column(horizontalAlignment = Alignment.End) {
                Row(horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                    Text(text = "🎯 $appearances", style = MaterialTheme.typography.bodySmall)
                    Text(text = "⚽ $goals", style = MaterialTheme.typography.bodySmall)
                    Text(text = "🎁 $assists", style = MaterialTheme.typography.bodySmall)
                }
            }
        }
    }
}
