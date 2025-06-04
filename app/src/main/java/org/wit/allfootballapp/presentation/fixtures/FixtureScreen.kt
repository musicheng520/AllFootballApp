package org.wit.allfootballapp.presentation.fixtures

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.room.util.TableInfo
import org.wit.allfootballapp.presentation.fixtures.components.FixtureItem

@Composable
fun FixtureScreen(
    teamId: Int,
    viewModel: FixtureViewModel = hiltViewModel(),
    onTeamClicked: (Int) -> Unit
) {
    val state by viewModel.state.collectAsState()

    // Load fixtures on first composition or when teamId changes
    LaunchedEffect(teamId) {
        viewModel.loadFixtures(teamId)
        viewModel.setEventHandler { event ->
            when(event) {
                is FixturesEvent.OnTeamClicked -> onTeamClicked(event.teamId)
                else -> {}
            }
        }
    }

    if(state.isLoading) {
        // 你可以自定义一个Loading组件
        CircularProgressIndicator()
    } else if(state.error != null) {
        Text(text = "Error: ${state.error}")
        Log.d("ERROR_MESSAGE", "${state.error}")
    } else {



        Column(modifier = Modifier.padding(20.dp)) {
            Text(
                text = "Matches",
                style = MaterialTheme.typography.headlineLarge,
                modifier = Modifier.padding(top = 12.dp)
            )
        }

        Column(modifier = Modifier.padding(20.dp, top = 80.dp)) {
            LazyColumn {
            items(state.fixtures) { fixture ->
                FixtureItem(fixtureInfo = fixture, onTeamClick = { clickedTeamId ->
                    viewModel.onEvent(FixturesEvent.OnTeamClicked(clickedTeamId))
                })
            }
        }}

    }
}
