package org.wit.allfootballapp.presentation.home_page.worker.player

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import org.wit.allfootballapp.domain.repository.PlayerRepository
import javax.inject.Inject

@HiltViewModel
class PlayerViewModel @Inject constructor(
    private val repository: PlayerRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(PlayerUiState(isLoading = false))
    val uiState: StateFlow<PlayerUiState> = _uiState

    fun onEvent(event: PlayerEvent) {
        when (event) {
            is PlayerEvent.LoadPlayers -> {
                loadPlayers(event.teamId, event.season)
            }
            is PlayerEvent.OnPlayerClick -> {
                // 这里一般只通知UI处理导航，也可以用SharedFlow发事件给UI
            }
        }
    }

    private fun loadPlayers(teamId: Int, season: Int) {
        viewModelScope.launch {
            _uiState.value = PlayerUiState(isLoading = true)
            try {
                val players = repository.getPlayersByTeam(teamId, season)
                _uiState.value = PlayerUiState(players = players)
            } catch (e: Exception) {
                _uiState.value = PlayerUiState(error = e.message)
            }
        }
    }
}
