package org.wit.allfootballapp.presentation.home_page.worker.player_detail

sealed class PlayerDetailEvent {

        data class LoadPlayer(val playerId: Int, val season: Int): PlayerDetailEvent()

}