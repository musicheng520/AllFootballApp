package org.wit.allfootballapp.presentation.home_page.worker.player_detail.components


import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun SeasonSelector(
    currentSeason: Int,
    modifier: Modifier,
    availableSeasons: List<Int> = listOf(2021, 2022, 2023),
    onSeasonSelected: (Int) -> Unit
) {
    Row(modifier = Modifier.padding(bottom = 16.dp)) {
        availableSeasons.forEach { season ->
            val isSelected = season == currentSeason
            Button(
                onClick = { onSeasonSelected(season) },
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (isSelected) MaterialTheme.colorScheme.primary
                    else MaterialTheme.colorScheme.secondaryContainer
                ),
                modifier = Modifier.padding(end = 8.dp)
            ) {
                Text(text = season.toString())
            }
        }
    }
}
