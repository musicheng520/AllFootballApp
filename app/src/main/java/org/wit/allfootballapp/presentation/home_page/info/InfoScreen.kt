package org.wit.allfootballapp.presentation.home_page.info


import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import coil.request.ImageRequest
import org.wit.allfootballapp.domain.model.team.TeamInfo

@Composable
fun InfoScreen(
    teamId: Int,
    onLogout: () -> Unit,
    viewModel: InfoViewModel = hiltViewModel()
) {
    LaunchedEffect(teamId) {
        viewModel.onEvent(InfoEvent.LoadInfo(teamId))
    }

    val state by viewModel.state.collectAsState()
    val scrollState = rememberScrollState()

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        when {
            state.isLoading -> {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    CircularProgressIndicator()
                }
            }
            state.error != null -> {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text(text = "加载失败：${state.error}", color = MaterialTheme.colorScheme.error)
                        Spacer(modifier = Modifier.height(16.dp))
                        Button(onClick = { viewModel.onEvent(InfoEvent.LoadInfo(teamId)) }) {
                            Text("重试")
                        }
                    }
                }
            }
            else -> {
                val teamInfo = state.teamInfo
                if (teamInfo != null) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(16.dp),
                        verticalArrangement = Arrangement.SpaceBetween
                    ) {
                        // 可滚动内容，weight 1f 让它占满剩余空间
                        Column(
                            modifier = Modifier
                                .weight(1f)
                                .verticalScroll(scrollState),
                            verticalArrangement = Arrangement.spacedBy(24.dp)
                        ) {
                            InfoContent(teamInfo)


                            // 固定底部按钮
                            Button(
                                onClick = {
                                    viewModel.logout {
                                        onLogout()
                                    }
                                },
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(48.dp)
                            ) {
                                Text("log out")
                            }
                        }


                    }
                } else {
                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        Text("暂无数据")
                    }
                }
            }
        }
    }
}

@Composable
fun InfoContent(teamInfo: TeamInfo) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        TeamInfoSection(teamInfo)
        VenueInfoSection(teamInfo.venue)
    }
}




