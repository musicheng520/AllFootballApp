package org.wit.allfootballapp.presentation.home_page.worker.player


import org.wit.allfootballapp.domain.model.playerList.PlayerWithStats

// UI状态
data class PlayerUiState(

    val isLoading: Boolean = false,
    val players: List<PlayerWithStats> = emptyList(),
    val error: String? = null
)