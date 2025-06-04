package org.wit.allfootballapp.presentation.home_page.worker.player

import org.wit.allfootballapp.presentation.home_page.transfer.TransferEvent

sealed class PlayerEvent {
    data class OnPlayerClick(val playerId: Int,val season:Int=2023) : PlayerEvent()
    data class LoadPlayers(val teamId: Int,val season:Int=2023) : PlayerEvent()
}