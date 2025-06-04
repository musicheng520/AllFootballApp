package org.wit.allfootballapp.presentation.home_page.worker.player_detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import org.wit.allfootballapp.domain.repository.PlayerInfoRepository

import javax.inject.Inject

@HiltViewModel
class PlayerDetailViewModel @Inject constructor(
    private val repository: PlayerInfoRepository
): ViewModel() {

    private val _state = MutableStateFlow(PlayerDetailState())
    val state: StateFlow<PlayerDetailState> = _state

    fun onEvent(event: PlayerDetailEvent) {
        when(event) {
            is PlayerDetailEvent.LoadPlayer -> {
                loadPlayer(event.playerId, event.season)
            }
        }
    }

    private fun loadPlayer(playerId: Int, seasonId: Int) {
        viewModelScope.launch {
            _state.value = PlayerDetailState(isLoading = true)

            try {
                val playerInfo = repository.getPlayerInfo(playerId,seasonId)

                if (playerInfo != null) {
                    _state.value = PlayerDetailState(player = playerInfo)
                } else {
                    _state.value = PlayerDetailState(error = "Player not found")
                }
            } catch (e: Exception) {
                _state.value = PlayerDetailState(error = e.message ?: "Unknown error")
            }
        }
    }
}

