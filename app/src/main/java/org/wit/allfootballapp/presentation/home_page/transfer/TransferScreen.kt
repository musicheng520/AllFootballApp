package org.wit.allfootballapp.presentation.home_page.transfer

import android.util.Log
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun TransferScreen(
    teamId: Int,
    viewModel: TransferViewModel = hiltViewModel(),

) {
    // 这里用 collectAsState() 把 StateFlow 转成 Compose State
    val state = viewModel.state.collectAsState().value

    LaunchedEffect(Unit) {
        viewModel.onEvent(TransferEvent.LoadTransfers(teamId)) // 示例 teamId
    }

    when {
        state.isLoading -> {
            CircularProgressIndicator()
        }
        state.error != null -> {
            Text("Error: ${state.error}")
            Log.d("ERROR_MESSAGE", "${state.error}")
        }
        else -> {
            LazyColumn {
                items(state.transfers) { transferInfo ->
                    TransferItem(transferInfo)
                }
            }
        }
    }
}
