package org.wit.allfootballapp.presentation.home_page.info

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import org.wit.allfootballapp.domain.model.team.VenueInfo

@Composable
fun VenueInfoSection(venueInfo: VenueInfo) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.secondaryContainer),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {

            Text(
                text = "Venue Info",
                style = MaterialTheme.typography.headlineSmall.copy(
                    fontWeight = FontWeight.SemiBold
                ),
                modifier = Modifier.padding(bottom = 12.dp)
            )

            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(venueInfo.imageUrl)
                    .crossfade(true)
                    .build(),
                contentDescription = "${venueInfo.name} image",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(180.dp)
                    .clip(RoundedCornerShape(12.dp))
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Name: ${venueInfo.name}",
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.Medium
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "Address: ${venueInfo.address}",
                style = MaterialTheme.typography.bodyMedium
            )
            Text(
                text = "City: ${venueInfo.city}",
                style = MaterialTheme.typography.bodyMedium
            )
            Text(
                text = "Capacity: ${venueInfo.capacity}",
                style = MaterialTheme.typography.bodyMedium
            )
            Text(
                text = "Surface: ${venueInfo.surface}",
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}
