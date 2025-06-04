package org.wit.allfootballapp.presentation.login.choose.components

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import org.wit.allfootballapp.R


@Composable
fun TeamIcon(
    teamId: Int,
    onClick: () -> Unit
) {
    val imageUrl = "https://media.api-sports.io/football/teams/$teamId.png"

    Column(
        modifier = Modifier
            .padding(8.dp)
            .clickable { onClick() },
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        AsyncImage(
            model = imageUrl,
            contentDescription = "Team Logo $teamId",
            modifier = Modifier
                .size(105.dp)
                .clip(CircleShape)
                .border(1.dp, MaterialTheme.colorScheme.primary, CircleShape),
            contentScale = ContentScale.Crop,
            error = painterResource(id = R.drawable.ic_network_error)
        )
        Spacer(modifier = Modifier.height(4.dp))
    }
}



