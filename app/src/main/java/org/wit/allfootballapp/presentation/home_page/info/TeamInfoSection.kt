package org.wit.allfootballapp.presentation.home_page.info

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import org.wit.allfootballapp.R
import org.wit.allfootballapp.domain.model.team.TeamInfo

@Composable
fun TeamInfoSection(teamInfo: TeamInfo) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primaryContainer),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(teamInfo.logoUrl)
                    .crossfade(true)
                    .build(),
                contentDescription = "${teamInfo.name} logo",
                modifier = Modifier
                    .size(72.dp)
                    .clip(RoundedCornerShape(8.dp)), // 不再是圆形，保留角
                contentScale = ContentScale.Crop,
                placeholder = painterResource(R.drawable.ic_time),
                error = painterResource(R.drawable.ic_network_error)
            )

            Spacer(modifier = Modifier.width(16.dp))

            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = teamInfo.name,
                    style = MaterialTheme.typography.headlineSmall.copy(
                        fontWeight = FontWeight.SemiBold
                    ),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "Code: ${teamInfo.code ?: "N/A"}",
                    style = MaterialTheme.typography.bodyMedium
                )
                Text(
                    text = "Country: ${teamInfo.country}",
                    style = MaterialTheme.typography.bodyMedium
                )
                Text(
                    text = "Founded: ${teamInfo.founded}",
                    style = MaterialTheme.typography.bodyMedium
                )
                Text(
                    text = "National Team: ${if (teamInfo.isNational) "Yes" else "No"}",
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }
    }
}
