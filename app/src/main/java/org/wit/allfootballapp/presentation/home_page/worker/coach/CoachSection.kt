package org.wit.allfootballapp.presentation.home_page.worker.coach

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import org.wit.allfootballapp.R

@Composable
fun CoachSection(
    teamId: Int,
    viewModel: CoachViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsState()

    LaunchedEffect(teamId) {
        viewModel.onEvent(CoachEvent.LoadCoach(teamId))
    }

    when {
        state.isLoading -> {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(24.dp),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }

        state.coach != null -> {
            val coach = state.coach!!
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                elevation = CardDefaults.cardElevation(4.dp),
                shape = MaterialTheme.shapes.medium
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    AsyncImage(
                        model = coach.photoUrl,
                        contentDescription = "Coach Photo",
                        modifier = Modifier
                            .size(80.dp)
                            .clip(CircleShape),
                        placeholder = painterResource(id = R.drawable.ic_time),
                        error = painterResource(id = R.drawable.ic_network_error)
                    )

                    Spacer(modifier = Modifier.width(16.dp))

                    Column {
                        Text(
                            text = coach.name,
                            style = MaterialTheme.typography.titleMedium
                        )
                        Text(
                            text = "Age: ${coach.age}",
                            style = MaterialTheme.typography.bodySmall
                        )
                    }
                }
            }
        }

        state.error != null -> {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Failed to load coach info.",
                    color = Color.Red,
                    style = MaterialTheme.typography.bodyMedium
                )
            }
            Log.e("CoachSection", "Error: ${state.error}")
        }
    }
}
