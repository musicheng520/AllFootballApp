package org.wit.allfootballapp.presentation.home_page.transfer

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import org.wit.allfootballapp.domain.model.transfer.Transfer
import org.wit.allfootballapp.domain.model.transfer.TransferInfo

@Composable
fun TransferItem(transferInfo: TransferInfo) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 12.dp, vertical = 8.dp),
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            // 球员名字和更新时间
            Text(
                text = transferInfo.player.name,
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.primary,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )

            Spacer(modifier = Modifier.height(4.dp))

            // 转会列表
            transferInfo.transfers.forEach { transfer ->
                Divider(modifier = Modifier.padding(vertical = 8.dp))
                TransferDetail(transfer)
            }
        }
    }
}

@Composable
fun TransferDetail(transfer: Transfer) {
    Column {
        Text(
            text = "Date: ${transfer.date}",
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )

        Spacer(modifier = Modifier.height(6.dp))

        Row(verticalAlignment = Alignment.CenterVertically) {
            TeamLogo(transfer.teams.`out`.logo, transfer.teams.`out`.name)
            Icon(
                imageVector = Icons.Default.ArrowForward,
                contentDescription = "Transfer Arrow",
                modifier = Modifier.padding(horizontal = 8.dp),
                tint = MaterialTheme.colorScheme.primary
            )
            TeamLogo(transfer.teams.`in`.logo, transfer.teams.`in`.name)
        }

        Spacer(modifier = Modifier.height(4.dp))

        Text(
            text = "Transfer type: ${transfer.type}",
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.secondary
        )
    }
}

@Composable
fun TeamLogo(logoUrl: String?, teamName: String) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        if (!logoUrl.isNullOrEmpty()) {
            AsyncImage(
                model = logoUrl,
                contentDescription = "$teamName Logo",
                modifier = Modifier
                    .size(32.dp)
                    .clip(RoundedCornerShape(4.dp))
            )
        } else {
            Box(
                modifier = Modifier
                    .size(32.dp)
                    .background(MaterialTheme.colorScheme.onSurface.copy(alpha = 0.1f), RoundedCornerShape(4.dp)),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = teamName.take(2).uppercase(),
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }

        Spacer(modifier = Modifier.width(8.dp))

        Text(
            text = teamName,
            style = MaterialTheme.typography.bodyMedium,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
    }
}
