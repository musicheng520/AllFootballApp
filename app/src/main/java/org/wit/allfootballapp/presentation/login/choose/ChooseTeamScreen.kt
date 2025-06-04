package org.wit.allfootballapp.presentation.login.choose

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import org.wit.allfootballapp.presentation.login.choose.components.TeamIcon




@Composable
fun ChooseTeamScreen(
    navController: NavController,
    viewModel: ChooseTeamViewModel = hiltViewModel(),
    onTeamSelected: () -> Unit,  // 新增回调
) {
    val teamIds = listOf(33, 34, 40, 42, 47, 49, 50, 66, 529, 530, 531, 536, 541, 157, 165, 168, 489, 492, 496, 505, 80, 85)
    val isTeamSaved by viewModel.isTeamSaved.collectAsState()
    val selectedTeamId by viewModel.teamId.collectAsState()

    Column(modifier = Modifier.padding(8.dp), horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = "Select your favorite team")
    }

    Column(modifier = Modifier.padding(10.dp, top = 20.dp)) {
        LazyVerticalGrid(
            columns = GridCells.Fixed(3),
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(16.dp)
        ) {
            items(teamIds) { id ->
                TeamIcon(teamId = id) {
                    viewModel.saveTeamId(id)
                }
            }
        }
    }

    LaunchedEffect(isTeamSaved, selectedTeamId) {
        if (isTeamSaved && selectedTeamId != null) {
            onTeamSelected()  // 触发回调，通知选队完成
            navController.navigate("main_screen/${selectedTeamId}") {
                popUpTo("choose_team_screen") { inclusive = true }
            }
        }
    }
}

