package org.wit.allfootballapp.presentation.home_page.worker.player_detail

import org.wit.allfootballapp.domain.model.playerInfo.PlayerInfo


data class PlayerDetailState(
val isLoading: Boolean = false,
val player: PlayerInfo? = null,
val error: String? = null
)



