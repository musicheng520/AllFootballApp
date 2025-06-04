package org.wit.allfootballapp.presentation.home_page.worker.player_detail.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter
import org.wit.allfootballapp.domain.model.playerInfo.Player

@Composable
fun PlayerInfoSection(player: Player, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AsyncImage(
            model = player.photoUrl,
            contentDescription = "Player Photo",
            modifier = Modifier
                .size(120.dp)
                .clip(CircleShape)
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(text = player.name, style = MaterialTheme.typography.titleLarge)
        Text(text =   " age: ${player.age},   ${player.nationality}")
        Text(text = " ${player.height ?: "N/A"}, ${player.weight ?: "N/A"}")
    }
}




