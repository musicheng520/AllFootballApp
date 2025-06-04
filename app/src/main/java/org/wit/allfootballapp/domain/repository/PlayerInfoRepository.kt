package org.wit.allfootballapp.domain.repository

import org.wit.allfootballapp.domain.model.playerInfo.PlayerInfo


interface PlayerInfoRepository {
    suspend fun getPlayerInfo(playerId:Int,seasonId: Int): PlayerInfo
}