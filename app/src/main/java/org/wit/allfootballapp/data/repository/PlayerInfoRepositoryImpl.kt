package org.wit.allfootballapp.data.repository

import org.wit.allfootballapp.data.mapper.toPlayerInfo
import org.wit.allfootballapp.data.remote.FootballApi
import org.wit.allfootballapp.domain.model.playerInfo.PlayerInfo
import org.wit.allfootballapp.domain.repository.PlayerInfoRepository
import javax.inject.Inject

class PlayerInfoRepositoryImpl @Inject  constructor(
    private val api: FootballApi
): PlayerInfoRepository{
    override suspend fun getPlayerInfo(
        playerId: Int,
        seasonId: Int
    ): PlayerInfo {
        return api.getPlayerInfo(playerId,seasonId).toPlayerInfo()
    }
}